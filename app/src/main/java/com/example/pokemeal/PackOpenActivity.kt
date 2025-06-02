package com.example.pokemeal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.example.pokemeal.databinding.ActivityPackDetailBinding
import com.example.pokemeal.databinding.ActivityPackOpenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.InputStreamReader

class PackOpenActivity: AppCompatActivity() {
    companion object {
        val EXTRA_PACK = "pack"
        val EXTRA_USER_ID = "userID"
    }

    private lateinit var binding: ActivityPackOpenBinding
    private lateinit var bottomNavigationView: BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pack = intent.getParcelableExtra<PackType>(PackOpenActivity.EXTRA_PACK)
        val userId = intent.getStringExtra(PackOpenActivity.EXTRA_USER_ID)
        val context =  binding.textViewDrawnMeal.context

        //draw random card from pack

        var drawnMeal = drawRandomMeal(context, pack!!.name)

        // set drawn meal
        binding.textViewDrawnMeal.text = drawnMeal?.strMeal
        val picasso = Picasso.Builder(context).build()

        picasso.load(drawnMeal?.strMealThumb).into(binding.imageViewDrawnMeal)

        // save to backendless
        drawnMeal!!.ownerId = userId
        saveToBackendless(drawnMeal!!)

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.selectedItemId = R.id.packs

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener { item ->
            val id = item.itemId

            when (id) {
                R.id.packs -> {
                    startActivity(Intent(applicationContext, PackListActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.meals -> {
                    startActivity(Intent(applicationContext, MealCollectionActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.foodLog -> {
                    startActivity(Intent(applicationContext, FoodLogActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                else -> false
            }
        }


    }

    fun drawRandomMeal(context: Context, strArea: String = "American"): Meal? {
        return try {
            // Load JSON from raw resources folder
            val inputStream = context.resources.openRawResource(R.raw.meals)
            val reader = InputStreamReader(inputStream)

            val gson = Gson()
            val recipeCards = gson.fromJson(reader, RecipeCards::class.java)

            val matchingMeals = recipeCards.meals.filter { meal ->
                meal.strArea?.equals(strArea, ignoreCase = true) == true
            }

            matchingMeals.randomOrNull()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun saveToBackendless(meal: Meal) {
        // code here to save to backendless
        Backendless.Data.of(Meal::class.java).save(meal, object : AsyncCallback<Meal?> {
            override fun handleResponse(response: Meal?) {
                Toast.makeText(this@PackOpenActivity, "${meal} has been successfully updated", Toast.LENGTH_SHORT).show()
            }

            override fun handleFault(fault: BackendlessFault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d("GameDetailActivity", "handleFault: ${fault.message}")
            }
        })
    }
}