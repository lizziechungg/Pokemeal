//package com.example.pokemeal
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
//
//class MealCollectionAdapter(var meals: List<Meal>):
//    RecyclerView.Adapter<PackListAdapter.ViewHolder>() {
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        val imageViewPackDisplayImage: ImageView
//        val layout: ConstraintLayout
//
//        init {
//            imageViewPackDisplayImage = view.findViewById(R.id.imageView_meal_image)
//
//            layout = view.findViewById(R.id.meal_collection)
//        }
//    }
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.item_pack, viewGroup, false)
//
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
//        val meal = meals[position]
//        val imageView = viewHolder.imageViewPackDisplayImage
//        val context = imageView.context
//
//        val picasso = Picasso.Builder(context).build()
//
//        picasso.load(meal?.strMealThumb).into(imageView)
//
//        viewHolder.layout.setOnClickListener{
//            val context = viewHolder.layout.context
//            val detailIntent = Intent(context, PackDetailActivity::class.java)
//            detailIntent.putExtra(MealDetailActivity.EXTRA_MEAL, meal)
//            context.startActivity(detailIntent)
//        }
//    }
//
//    override fun getItemCount() = meals.size
//
//}