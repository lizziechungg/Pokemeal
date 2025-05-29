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
    }
    private lateinit var binding: ActivityPackDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pack = intent.getParcelableExtra<PackType>(EXTRA_PACK)


        // pokemon types
        binding.imageViewPackImage.setImageDrawable(getDrawable(resources.getIdentifier(pack?.resourceId, "drawable", packageName)))

        binding.imageViewPackImage.setOnClickListener{
            val context = binding.imageViewPackImage.context
            val detailIntent = Intent(context, PackOpenActivity::class.java)
            detailIntent.putExtra(PackOpenActivity.EXTRA_PACK, pack)
            context.startActivity(detailIntent)
        }


    }

}