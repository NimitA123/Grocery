package com.example.shoppingapp.models.localDatabase




import androidx.room.TypeConverter
import com.example.shoppingapp.models.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromItemList(itemList: List<Item>?): String {
        return Gson().toJson(itemList)
    }

    @TypeConverter
    fun toItemList(itemListString: String): List<Item>? {
        val itemType = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(itemListString, itemType)
    }
}
