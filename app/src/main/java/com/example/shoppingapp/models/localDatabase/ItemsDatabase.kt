package com.example.shoppingapp.models.localDatabase


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.models.Category

@Database(entities = [ Category::class], version = 2)
@TypeConverters(Converters::class)
abstract class ItemsDatabase:RoomDatabase() {
    abstract fun getItemDAO() : ItemsDao



    companion object{
        private var instance: ItemsDatabase? = null


        fun getItemsDatabase(context: Context) : ItemsDatabase{

            if (instance != null){
                return instance!!
            }else{
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    ItemsDatabase::class.java,
                    "Itemsdb"
                )

                builder.fallbackToDestructiveMigration()
                instance = builder.build()
            }

            return instance!!
        }
    }

}