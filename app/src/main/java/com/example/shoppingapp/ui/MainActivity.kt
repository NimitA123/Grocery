package com.example.shoppingapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CategoryAdapter
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.models.Category
import com.example.shoppingapp.models.localDatabase.*
import com.example.shoppingapp.reposirity.ItemsRepository
import com.example.shoppingapp.utils.Constants
import com.example.shoppingapp.viewModel.ItemsViewModel
import com.example.shoppingapp.viewModel.MainItemsViewModelFactory


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var itemsViewModel: ItemsViewModel
    lateinit var itemsDao: ItemsDao
    lateinit var itemsDatabase: ItemsDatabase
    lateinit var categoryAdapter: CategoryAdapter
    var categoryItem  = arrayListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
       // supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.orange)))
        window.statusBarColor = ContextCompat.getColor(this, R.color.orange)
        setData()
        onClick()
        localDatabase()
    }

    private fun setData() {
        Constants.addToCartQuantityLiveData.observe(this, Observer {
            binding?.redCircleText?.text = it
        })

    }

    private fun onClick() {
      binding?.favouriteIcon?.setOnClickListener {
          startActivity(Intent(this, FavouriteActivity::class.java))
      }
        binding?.addCardIcon?.setOnClickListener {
            startActivity(Intent(this, AddToCartActivity::class.java))
        }

    }

    private fun localDatabase() {
        itemsDatabase = ItemsDatabase.getItemsDatabase(this)
        itemsDao = itemsDatabase.getItemDAO()
        val itemsRepository = ItemsRepository(itemsDao = itemsDao)
         itemsViewModel = ViewModelProvider(
            this,
            MainItemsViewModelFactory(itemsRepository)
        ).get(ItemsViewModel::class.java)

        itemsViewModel.getItemFromDatabase()

        // Observe the LiveData to get the stored data
        itemsViewModel.categoriesWithItems.observe(this, Observer { categories ->
            Log.d("categoryItems", "${categories}")
            categoryItem.addAll(categories)
            if(categoryItem != null){
                setRecyclerView()
            }
            })



    }

    private fun setRecyclerView() {
        categoryAdapter = CategoryAdapter(categoryItem){ position, arrayList ->

        }
       val linearLayoutManager  = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.categoryRecyclerView?.apply {
            adapter = categoryAdapter
            layoutManager = linearLayoutManager

        }
    }
}
