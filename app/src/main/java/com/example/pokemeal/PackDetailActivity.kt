package com.example.pokemeal

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.TypedArrayUtils.getResourceId
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemeal.databinding.ActivityPackDetailBinding

class PackDetailActivity: AppCompatActivity() {
    companion object {
        val EXTRA_PACK = "pack"
        val EXTRA_USER_ID = "userID"
    }
    private lateinit var binding: ActivityPackDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pack = intent.getParcelableExtra<PackType>(EXTRA_PACK)

        val userID = intent.getStringExtra(EXTRA_USER_ID)



        // pokemon types
        binding.imageViewPackImage.setImageDrawable(getDrawable(resources.getIdentifier(pack?.resourceId, "drawable", packageName)))

        binding.imageViewPackImage.setOnClickListener{
            val context = binding.imageViewPackImage.context
            val detailIntent = Intent(context, PackOpenActivity::class.java)
            detailIntent.putExtra(PackOpenActivity.EXTRA_PACK, pack)
            detailIntent.putExtra(PackOpenActivity.EXTRA_USER_ID, userID)
            context.startActivity(detailIntent)
        }

//
//
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.selectedItemId = R.id.packs
//
//        // Perform item selected listener
//        bottomNavigationView.setOnItemSelectedListener { item ->
//            val id = item.itemId
//
//            when (id) {
//                R.id.packs -> {
//                    startActivity(Intent(applicationContext, PackListActivity::class.java))
//                    overridePendingTransition(0, 0)
//                    true
//                }
//
//                R.id.recipes -> {
//                    startActivity(Intent(applicationContext, MealCollectionActivity::class.java))
//                    overridePendingTransition(0, 0)
//                    true
//                }
//                R.id.foodLog -> {
//                    startActivity(Intent(applicationContext, FoodLogActivity::class.java))
//                    overridePendingTransition(0, 0)
//                    true
//                }
//
//                else -> false
//            }
//        }

    }

}