package com.example.pokemeal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemeal.databinding.ActivityPackDetailBinding
import com.example.pokemeal.databinding.ActivityPackOpenBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.InputStreamReader

class PackOpenActivity: AppCompatActivity() {
    companion object {
        val EXTRA_PACK = "pack"
    }

    private lateinit var binding: ActivityPackOpenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pack = intent.getParcelableExtra<PackType>(PackOpenActivity.EXTRA_PACK)
        val context =  binding.textViewDrawnMeal.context

        //draw random card from pack

        var meal = drawRandomMeal(context, pack!!.name)

        // set drawn meal
        binding.textViewDrawnMeal.text = meal?.strMeal
        val picasso = Picasso.Builder(context).build()

        picasso.load(meal?.strMealThumb).into(binding.imageViewDrawnMeal)


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
}