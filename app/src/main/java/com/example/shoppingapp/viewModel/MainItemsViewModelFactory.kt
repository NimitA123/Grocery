package com.example.shoppingapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapp.reposirity.ItemsRepository


class MainItemsViewModelFactory(private val itemsRepository: ItemsRepository) :  ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
          return  ItemsViewModel(itemsRepository) as T
      }
}