package com.example.shoppingapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.AddCartCardBinding

import com.example.shoppingapp.models.Item
import com.example.shoppingapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class AddToCartAdapter(
    var favouriteItems: ArrayList<Item>,
    var subTotalPrice: Int,
    param: (Any, Any) -> Unit
) : RecyclerView.Adapter<AddToCartAdapter.AddToCartViewHolder>() {

    inner class AddToCartViewHolder(var addToCartCardBinding: AddCartCardBinding) : RecyclerView.ViewHolder(addToCartCardBinding.root) {
        private var quantity = 1
        private var subTotalQuantity   = 0


        fun bind(category: Item) {
           subTotalQuantity = favouriteItems.size
            Log.d("subTotalQuantity", "${subTotalQuantity}")
            Glide.with(itemView).load(category.icon).into(addToCartCardBinding.itemImage)
            addToCartCardBinding.itemName.text  = category.name
            addToCartCardBinding.itemPrice.text  = category.price.toString()
            addToCartCardBinding.quantityPriceText.text = category.price.toString()
            addToCartCardBinding.removeIcon.setOnClickListener {
                decrementQuantity(category)
            }
            addToCartCardBinding.add.setOnClickListener {
                incrementQuantity(category)
            }
        }
        private fun incrementQuantity(category: Item) {
            subTotalQuantity++
            quantity++
            updateQuantity(category, true)
        }

        private fun decrementQuantity(category: Item) {
            if (quantity > 0) {
                quantity--
                subTotalQuantity--
                updateQuantity(category, false)
            }
        }

        private fun updateQuantity(category: Item, b: Boolean) {
            addToCartCardBinding.quantityText.text = quantity.toString()
            addToCartCardBinding.quantityPriceText.text = (category.price * quantity).toString()
            if(b){
                subTotalPrice =  subTotalPrice + ((category.price.toInt()))
            }
            else{
                subTotalPrice =  subTotalPrice - ((category.price.toInt()))
            }

            CoroutineScope(Dispatchers.IO).launch {
                Constants.subTotalPrice.postValue(subTotalPrice.toString())
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AddToCartViewHolder {
        return AddToCartViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.add_cart_card, parent, false))
    }


    override fun onBindViewHolder(holder: AddToCartViewHolder, position: Int) {

        holder.bind(favouriteItems[position])
    }

    override fun getItemCount(): Int = favouriteItems.size


    /*  fun addData(list: List<GameOfThrones>) {
          gameOfThronesHouseList = list
          notifyDataSetChanged()
      }*/


}