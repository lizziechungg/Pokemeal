package com.example.pokemeal

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pokemeal.databinding.ActivityMealDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MealDetailActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var webView: WebView
    private lateinit var binding: ActivityMealDetailBinding

    companion object {
        val EXTRA_MEAL = "meal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_pack_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val meal = intent.getParcelableExtra<Meal>(EXTRA_MEAL)

        binding.textViewRecipeName.setText(meal?.strMeal)
        binding.textViewRecipeArea.setText(meal?.strArea)
        binding.textViewRecipeType.setText(meal?.strCategory)
        binding.textViewRecipeInstructions.setText(meal?.strInstructions)

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
}