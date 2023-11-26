package com.example.shoppingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.AddToCartAdapter
import com.example.shoppingapp.databinding.ActivityAddToCartBinding
import com.example.shoppingapp.utils.Constants

class AddToCartActivity : AppCompatActivity() {
    private var binding: ActivityAddToCartBinding? = null
    private lateinit var addToCartAdapter: AddToCartAdapter
    private  var subTotalPrice = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_to_cart)

        setData()
        binding?.toolbarLayout?.toolbarText?.text  = "Add To Cart"
        onClick()
        setRecyclerView()

    }

    private fun setData() {
        Constants.subTotalPriceLiveData.observe(this, Observer {

            binding?.textSubtotalPrice?.text = it
            binding?.textTotalPrice?.text = (binding?.textSubtotalPrice?.text.toString().toInt() - 20).toString()
        })
    }

    private fun onClick() {
        binding?.toolbarLayout?.backArrow?.setOnClickListener {
            finish()
        }
    }

    private fun setRecyclerView() {
        Constants.addToCartItem.forEach {
            subTotalPrice += it.price.toInt()
        }
        addToCartAdapter   = AddToCartAdapter(Constants.addToCartItem, subTotalPrice){ position, arrayList ->

        }
        binding?.textSubtotalPrice?.text = subTotalPrice.toString()
        binding?.textTotalPrice?.text = (binding?.textSubtotalPrice?.text.toString().toInt() - 20).toString()

        val linearLayoutManager  = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.favouriteRecyclerView?.apply {
            adapter = addToCartAdapter
            layoutManager = linearLayoutManager
        }
    }
}