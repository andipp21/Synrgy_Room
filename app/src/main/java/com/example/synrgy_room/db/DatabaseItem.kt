package com.example.synrgy_room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.synrgy_room.Item
import com.example.synrgy_room.ItemDao

@Database(entities = [Item::class], version = 1)
abstract class DatabaseItem: RoomDatabase() {
    abstract fun itemDao() : ItemDao

    companion object{
        private var INSTANCE: DatabaseItem? = null

        fun getInstance(context: Context): DatabaseItem? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    DatabaseItem::class.java,
                    "item_db").build()
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}