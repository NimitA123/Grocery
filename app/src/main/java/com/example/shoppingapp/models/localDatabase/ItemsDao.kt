package com.example.shoppingapp.models.localDatabase




import androidx.room.*
import com.example.shoppingapp.models.Category

@Dao
interface ItemsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)


    @Query("SELECT * FROM category_table")
    suspend fun getCategoriesByName(): List<Category>
/*    @Query("SELECT * FROM items")
    fun getAll(): List<FoodEntity>
    @Update
    fun updateTask(foodEntity: FoodEntity)
    @Delete
    fun deleteTask(foodEntity: FoodEntity)*/
}

