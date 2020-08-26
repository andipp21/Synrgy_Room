package com.example.synrgy_room.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.synrgy_room.ItemAdapter
import com.example.synrgy_room.R
import com.example.synrgy_room.add.AddActivity
import com.example.synrgy_room.db.DatabaseItem
import com.example.synrgy_room.db.Item
import com.example.synrgy_room.edit.EditActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.Listener {

    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DatabaseItem.getInstance(this)?.let {
            presenter = MainActivityPresenter(it, this)
        }

        fabAdd.setOnClickListener {
            presenter.goToAddActivity()
        }

    }

    override fun onResume() {
        super.onResume()

        presenter.fetchData()
    }

    override fun showStudentList(listItem: List<Item>){
        runOnUiThread{
            val adapter = ItemAdapter(listItem, presenter)
            rvContainer.layoutManager=
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            rvContainer.adapter = adapter
        }
    }

    override fun goToAddActivity() {
        val intentGoToActivityAdd = Intent(this, AddActivity::class.java)

        startActivity(intentGoToActivityAdd)
    }

    override fun goToEditActivity(item: Item) {
        val intentGoToActivityEdit = Intent(this, EditActivity::class.java)
        intentGoToActivityEdit.putExtra("item", item)
        startActivity(intentGoToActivityEdit)
    }

    override fun showDeletedSuccess(item: Item) {
        runOnUiThread {
            Toast.makeText(this, "Data ${item.name} Telah dihapus", Toast.LENGTH_LONG).show()
            presenter.fetchData()
        }
    }

    override fun showDeletedFailed(item: Item) {
        runOnUiThread {
            Toast.makeText(this, "Data ${item.name} Gagal dihapus", Toast.LENGTH_LONG).show()
        }
    }
}