package com.example.pokemeal

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PackListAdapter (var types: List<PackType>):
    RecyclerView.Adapter<PackListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageViewPackDisplayImage: ImageView
        val layout: ConstraintLayout

        init {
            imageViewPackDisplayImage = view.findViewById(R.id.imageView_pack_display_image)

            layout = view.findViewById(R.id.layout_packs)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_pack, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
        val packType = types[position]
        val imageView = viewHolder.imageViewPackDisplayImage
        val context = imageView.context

        loadDrawableByName(context, packType.resourceId, imageView)

        viewHolder.layout.setOnClickListener{
            val context = viewHolder.layout.context
            val detailIntent = Intent(context, PackDetailActivity::class.java)
            detailIntent.putExtra(PackDetailActivity.EXTRA_PACK, packType)
            context.startActivity(detailIntent)
        }
    }

    private fun loadDrawableByName(context: Context, resourceName: String?, imageView: ImageView) {
        val resourceId = context.resources.getIdentifier(
            resourceName ?: "",
            "drawable",
            context.packageName
        )

        val drawable = if (resourceId != 0) {
            ContextCompat.getDrawable(context, resourceId)
        } else {
            ContextCompat.getDrawable(context, R.drawable.american) // fallback
        }

        imageView.setImageDrawable(drawable)
    }

    override fun getItemCount() = types.size

}