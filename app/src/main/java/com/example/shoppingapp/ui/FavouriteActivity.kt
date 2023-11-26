package com.example.shoppingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CategoryAdapter
import com.example.shoppingapp.adapter.FavouriteAdapter
import com.example.shoppingapp.databinding.ActivityFavouriteBinding
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.databinding.FavouriteItemCardBinding
import com.example.shoppingapp.utils.Constants

class FavouriteActivity : AppCompatActivity() {
    private var binding: ActivityFavouriteBinding? = null
    private lateinit var favouriteAdapter: FavouriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite)

        onClick()
         setRecyclerView()
    }
    private fun onClick() {
        binding?.toolbarLayout?.backArrow?.setOnClickListener {
            finish()
        }
    }

    private fun setRecyclerView() {
      favouriteAdapter   = FavouriteAdapter(Constants.favouriteItem){ position, arrayList ->

      }
        val linearLayoutManager  = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.favouriteRecyclerView?.apply {
            adapter = favouriteAdapter
            layoutManager = linearLayoutManager

        }
    }
}