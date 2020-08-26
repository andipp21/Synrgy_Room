package com.example.synrgy_room.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.synrgy_room.db.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM Item")
    fun getAllItem(): List<Item>

    @Insert(onConflict = REPLACE)
    fun addItem(item: Item): Long

    @Delete
    fun deleteItem(item: Item): Int

    @Update
    fun updateItem(item: Item): Int
}