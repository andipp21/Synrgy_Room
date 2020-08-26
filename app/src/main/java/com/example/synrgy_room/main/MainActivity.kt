package com.example.synrgy_room.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.synrgy_room.DatabaseItem
import com.example.synrgy_room.ItemAdapter
import com.example.synrgy_room.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DatabaseItem.getInstance(this)?.let {
            db = it
        }

        fabAdd.setOnClickListener {
            val intentGoToActivityAdd = Intent(this, AddActivity::class.java)

            startActivity(intentGoToActivityAdd)
        }

    }

    override fun onResume() {
        super.onResume()

        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val listItem = db.itemDao().getAllItem()
            runOnUiThread{
                val adapter = ItemAdapter(listItem)
                rvContainer.layoutManager=LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                rvContainer.adapter = adapter
            }
        }
    }
}