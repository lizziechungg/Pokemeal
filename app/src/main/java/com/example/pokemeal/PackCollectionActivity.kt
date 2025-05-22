package com.example.pokemeal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class PackCollectionActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_collection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.collection)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.selectedItemId = R.id.recipes

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener { item ->
            val id = item.itemId

            when (id) {
                R.id.packs -> {
                    startActivity(Intent(applicationContext, PackListActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.recipes -> {
                    startActivity(Intent(applicationContext, PackCollectionActivity::class.java))
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