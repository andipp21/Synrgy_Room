package com.example.synrgy_room.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.synrgy_room.R
import com.example.synrgy_room.db.DatabaseItem
import com.example.synrgy_room.db.Item
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(), AddActivityPresenter.Listener {
    private lateinit var presenter: AddActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        DatabaseItem.getInstance(this)?.let {
            presenter = AddActivityPresenter(it, this)
        }

        btnSave.setOnClickListener {
            val item = Item(null, etName.text.toString(), etQuantity.text.toString().toInt())

            presenter.saveItem(item)
        }
    }

    override fun showSaveSuccess() {
        runOnUiThread {
            Toast.makeText(this, "Data telah disimpan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun showSaveFailed() {
        runOnUiThread {
            Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}