package com.example.shoppingapp.adapter





import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.CategoryCardBinding
import com.example.shoppingapp.databinding.FavouriteItemCardBinding
import com.example.shoppingapp.models.Item


open class FavouriteAdapter(var favouriteItems: ArrayList<Item>, param: (Any, Any) -> Unit) : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    inner class FavouriteViewHolder(var favouriteItemCardBinding: FavouriteItemCardBinding) : RecyclerView.ViewHolder(favouriteItemCardBinding.root) {
        fun bind(category: Item) {
            Glide.with(itemView).load(category.icon).into(favouriteItemCardBinding.itemImage)
            favouriteItemCardBinding.itemName.text  = category.name
            favouriteItemCardBinding.itemPrice.text  = category.price.toString()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : FavouriteViewHolder {
        return FavouriteViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.favourite_item_card, parent, false))
    }


    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(favouriteItems[position])
    }

    override fun getItemCount(): Int = favouriteItems.size


    /*  fun addData(list: List<GameOfThrones>) {
          gameOfThronesHouseList = list
          notifyDataSetChanged()
      }*/


}