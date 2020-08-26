package com.example.synrgy_room.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.synrgy_room.DatabaseItem
import com.example.synrgy_room.Item
import com.example.synrgy_room.R
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var db: DatabaseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        DatabaseItem.getInstance(this)?.let {
            db = it
        }

        btnSave.setOnClickListener {
            val item = Item(null,etName.text.toString(), etQuantity.text.toString().toInt())

            GlobalScope.launch {
                val totalSaved = db.itemDao().addItem(item)

                runOnUiThread{
                    if (totalSaved>0){
                        Toast.makeText(it.context, "Data telah disimpan", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@AddActivity, "Data gagal disimpan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}