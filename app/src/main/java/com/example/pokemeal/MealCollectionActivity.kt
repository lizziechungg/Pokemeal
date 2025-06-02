package com.example.pokemeal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.example.pokemeal.databinding.ActivityMealCollectionBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MealCollectionActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
//    private lateinit var adapter: MealCollectionAdapter
    private lateinit var binding: ActivityMealCollectionBinding



    companion object {
        val EXTRA_USER_ID = "user"
    }
    private fun getDataFromBackendless(){
        val userId = intent.getStringExtra(LoginActivity.EXTRA_USER_ID)
        val whereClause = "ownerId = '$userId'"
        val queryBuilder = DataQueryBuilder.create()
        queryBuilder.setWhereClause(whereClause)

        // make backendless call to retrieve all data
        Backendless.Data.of(Meal::class.java).find(queryBuilder, object :
            AsyncCallback<List<Meal>> {
            override fun handleResponse(foundMeals: List<Meal>) {
                // all GameEntry instances have been found
                Log.d("MealCollectionActivity", "handleResponse: $foundMeals")
//                adapter = MealCollectionAdapter(foundMeals.toMutableList())
//                binding.recyclerViewRecipeList.adapter = adapter
//                binding.recyclerViewRecipeList.layoutManager =
//                    LinearLayoutManager(this@MealCollectionActivity)
            }

            override fun handleFault(fault: BackendlessFault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d("MealCollectionActivity", "handleFault: ${fault.message}")
            }
        })
        // in the handleResponse, get the list of data and constructor the adapter & apply to the reyclerview

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_meal_collection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.collection)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.selectedItemId = R.id.meals

        // get the user id from the intent and pass it to
        val userId = intent.getStringExtra(LoginActivity.EXTRA_USER_ID)

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener { item ->
            val id = item.itemId

            when (id) {
                R.id.packs -> {
                    val packListIntent = Intent(applicationContext, PackListActivity::class.java)
                    packListIntent.putExtra(com.example.pokemeal.LoginActivity.EXTRA_USER_ID, userId)
                    startActivity(packListIntent)

                    overridePendingTransition(0, 0)
                    true
                }

                R.id.meals -> {
                    val mealCollectionIntent = Intent(applicationContext, MealCollectionActivity::class.java)
                    mealCollectionIntent.putExtra(com.example.pokemeal.LoginActivity.EXTRA_USER_ID, userId)
                    startActivity(mealCollectionIntent)
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }


    }
}