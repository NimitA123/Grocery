package com.example.shoppingapp.reposirity

import com.example.shoppingapp.models.Category
import com.example.shoppingapp.models.Item
import com.example.shoppingapp.models.JsonDataProvider
import com.example.shoppingapp.models.ProductResponse
import com.example.shoppingapp.models.localDatabase.ItemsDao
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemsRepository (private val itemsDao: ItemsDao) {
      fun storeItem() {
        val jsonString = JsonDataProvider.jsonString// your JSON string
        val productResponse: ProductResponse =
            Gson().fromJson(jsonString, ProductResponse::class.java)
        // Accessing categories
        val categories: List<Category> = productResponse.categories
          for (category in categories) {
   /*         val categoryId: Int = category.id
            val categoryName: String = category.name
            val items: List<Item> = category.items
            for (item in items) {
                val itemId: Int = item.id
                val itemName: String = item.name
                val itemIcon: String = item.icon
                val itemPrice: Double = item.price
                val category = Category(
                    id = 0,
                    name = categoryName,
                    items = listOf(Item(itemId, itemName, itemIcon, itemPrice))
                )

            }*/

              CoroutineScope(Dispatchers.IO).launch {
                  itemsDao.insert(category)
              }
        }
    }

    suspend fun getStoredData(): List<Category> {
        return withContext(Dispatchers.IO) {
            itemsDao.getCategoriesByName()
        }
    }
}




