package com.example.synrgy_room.add

import com.example.synrgy_room.db.DatabaseItem
import com.example.synrgy_room.db.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivityPresenter (val db: DatabaseItem, val listener: Listener){

    fun saveItem(item: Item){
        GlobalScope.launch {
            val totalSaved = db.itemDao().addItem(item)

            if (totalSaved > 0) {
                listener.showSaveSuccess()
            } else {
                listener.showSaveFailed()
            }
        }
    }

    interface Listener{
        fun showSaveSuccess()
        fun showSaveFailed()
    }
}