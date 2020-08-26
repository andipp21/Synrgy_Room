package com.example.synrgy_room.edit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.synrgy_room.R
import com.example.synrgy_room.db.DatabaseItem
import com.example.synrgy_room.db.Item
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var db: DatabaseItem

    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        DatabaseItem.getInstance(this)?.let {
            db = it
        }

        intent.getParcelableExtra<Item>("item")?.let {
            item = it
        }

        etNameEdit.setText(item.name)

        etQuantityEdit.setText(item.quantity.toString())

        btnEdit.setOnClickListener {
            item.apply {
                name = etNameEdit.text.toString()
                quantity = etQuantityEdit.text.toString().toInt()
            }

            GlobalScope.launch {
                val rowUpdated = db.itemDao().updateItem(item)

                runOnUiThread {
                    if(rowUpdated>0){
                        Toast.makeText(this@EditActivity,"Data Telah Terupdate", Toast.LENGTH_LONG).show()
                        this@EditActivity.finish()
                    } else {
                        Toast.makeText(this@EditActivity,"Data Gagal diupdate", Toast.LENGTH_LONG).show()
                    }
                }
            }


        }

    }
}