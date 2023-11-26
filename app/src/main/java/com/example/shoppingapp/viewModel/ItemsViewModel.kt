package com.example.shoppingapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.models.Category
import com.example.shoppingapp.reposirity.ItemsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemsViewModel (private val itemsRepository: ItemsRepository) : ViewModel() {
    private val _categoriesWithItems = MutableLiveData<List<Category>>()
    val categoriesWithItems: LiveData<List<Category>> get() = _categoriesWithItems
    fun getItemFromDatabase() {
        viewModelScope.launch {
            itemsRepository.storeItem()
            val storedData = itemsRepository.getStoredData()
            _categoriesWithItems.postValue(storedData)
        }
    }
}


