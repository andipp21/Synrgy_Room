package com.example.synrgy_room.main

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.synrgy_room.ItemAdapter
import com.example.synrgy_room.add.AddActivity
import com.example.synrgy_room.db.DatabaseItem
import com.example.synrgy_room.db.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityPresenter(val db: DatabaseItem, val listener: Listener) {

    fun fetchData(){
        GlobalScope.launch {
            val listItem = db.itemDao().getAllItem()
            listener.showStudentList(listItem)
        }
    }

    fun goToAddActivity(){
        listener.goToAddActivity()
    }

    fun goToEditActivity(item: Item){
        listener.goToEditActivity(item)
    }

    fun deleteItem(item: Item){
        GlobalScope.launch {
            val rowDeleted = db.itemDao().deleteItem(item)
            if (rowDeleted > 0){
                listener.showDeletedSuccess(item)
            } else {
                listener.showDeletedFailed(item)
            }
        }
    }

    interface Listener{
        fun showStudentList(listItem: List<Item>)
        fun goToAddActivity()
        fun goToEditActivity(item: Item)
        fun showDeletedSuccess(item: Item)
        fun showDeletedFailed(item: Item)
    }
}