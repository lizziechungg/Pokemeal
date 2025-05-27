package com.example.pokemeal

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pokemeal.databinding.ActivityPackDetailBinding
import com.example.pokemeal.databinding.ActivityPackListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class PackDetailActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var webView: WebView
    private lateinit var binding: ActivityPackDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPackDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_pack_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



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