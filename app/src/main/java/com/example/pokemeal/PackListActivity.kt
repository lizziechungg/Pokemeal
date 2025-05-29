package com.example.pokemeal

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemeal.databinding.ActivityPackListBinding
import com.google.gson.Gson

class PackListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPackListBinding
    private lateinit var adapter: PackListAdapter

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


        adapter = PackListAdapter(jsonData.types)
        binding.recyclerViewPacklistCards.adapter = adapter
        binding.recyclerViewPacklistCards.layoutManager =
            GridLayoutManager(this@PackListActivity, 2)


    }
}