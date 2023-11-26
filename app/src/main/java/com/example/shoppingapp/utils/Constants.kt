package com.example.shoppingapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppingapp.models.Category
import com.example.shoppingapp.models.Item

object Constants {
    var AppUrl  = ""
    var favouriteItem  = arrayListOf<Item>()
    var addToCartItem = arrayListOf<Item>()
     val subTotalPrice = MutableLiveData<String>()
    val subTotalPriceLiveData: LiveData<String> get() = subTotalPrice
    val addToCartQuantity = MutableLiveData<String>()
    val addToCartQuantityLiveData: LiveData<String> get() = addToCartQuantity
    var addToCart  =0



}