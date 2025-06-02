package com.example.pokemeal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemeal.databinding.ActivityPackListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class PackListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPackListBinding
    private lateinit var adapter: PackListAdapter
    private lateinit var bottomNavigationView: BottomNavigationView


    companion object {
        val TAG = "Pack List Activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPackListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.packs)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val jsonData = gson.fromJson(jsonString, PackTypes::class.java)
        Log.d(TAG, "onResponse: ${jsonData}")

        val userId = intent.getStringExtra(LoginActivity.EXTRA_USER_ID)

        adapter = PackListAdapter(jsonData.types, userId)
        binding.recyclerViewPacklistCards.adapter = adapter
        binding.recyclerViewPacklistCards.layoutManager =
            GridLayoutManager(this@PackListActivity, 2)


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