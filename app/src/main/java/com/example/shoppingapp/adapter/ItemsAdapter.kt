
package com.example.shoppingapp.adapter




import android.graphics.PorterDuff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ItemsCardBinding
import com.example.shoppingapp.models.Item
import com.example.shoppingapp.utils.Constants
import com.example.shoppingapp.utils.showToast
import com.github.alexzhirkevich.customqrgenerator.style.Color
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemsAdapter(var categoryItems: List<Item>, param: (Any, Any) -> Unit) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.items_card, parent, false))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val data = categoryItems[position]
        holder.setData(data)

    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }
    inner class ItemsViewHolder(var _binding: ItemsCardBinding) : RecyclerView.ViewHolder(_binding.root) {

        fun setData(data: Item) {
            _binding.data = data
            _binding.itemName.text  = data.name
            _binding.itemPrice.text  = data.price.toString()
            Glide.with(itemView).load(data.icon).into(_binding.itemImage)


            _binding.favouriteIcon.setOnClickListener {

                if(!data.isFavourite){
                    data.toggleFavourite()
                    Constants.favouriteItem.add(data)
                    _binding.favouriteIcon.setImageResource(R.drawable.favorite_icon)
                    itemView.context.showToast(itemView, "Add to Favourite")
                }
                else{
                    data.toggleFavourite()
                    Constants.favouriteItem.remove(data)
                    _binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    itemView.context.showToast(itemView, "UnFavourite")
                }
            }
            _binding.addToCart.setOnClickListener {
                Constants.addToCartItem.add(data)
                Constants.addToCart  = Constants.addToCart + 1

                CoroutineScope(Dispatchers.IO).launch {
                    Constants.addToCartQuantity.postValue(Constants.addToCart.toString())
                }
                itemView.context.showToast(itemView, "Add to Cart")
            }
        }
    }

}

