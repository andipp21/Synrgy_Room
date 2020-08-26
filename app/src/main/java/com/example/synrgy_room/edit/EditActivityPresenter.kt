package com.example.synrgy_room.edit

import android.widget.Toast
import com.example.synrgy_room.db.DatabaseItem
import com.example.synrgy_room.db.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivityPresenter(val db: DatabaseItem, val listener: Listener) {
    interface Listener {
        fun showEditSuccess()
        fun showEditFailed()
    }

    fun editItem(item: Item) {
        GlobalScope.launch {
            val rowUpdated = db.itemDao().updateItem(item)

            if (rowUpdated > 0) {
                listener.showEditSuccess()
            } else {
                listener.showEditFailed()
            }
        }
    }

}