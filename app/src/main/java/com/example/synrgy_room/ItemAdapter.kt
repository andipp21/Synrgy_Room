package com.example.synrgy_room

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.synrgy_room.edit.EditActivity
import kotlinx.android.synthetic.main.stuff_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemAdapter(val listItem: List<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private lateinit var db: DatabaseItem

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stuff_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvName.text = listItem[position].name
        holder.itemView.tvQuantity.text = listItem[position].quantity.toString()

        DatabaseItem.getInstance(holder.itemView.context)?.let {
            db = it
        }

        holder.itemView.btnHapus.setOnClickListener{
            GlobalScope.launch {
                val rowDeleted = db.itemDao().deleteItem(listItem[position])
                (holder.itemView.context as MainActivity).runOnUiThread {
                    if (rowDeleted > 0) {
                        Toast.makeText(holder.itemView.context, "Data ${listItem[position].name} Telah dihapus", Toast.LENGTH_LONG)
                    } else {
                        Toast.makeText(holder.itemView.context, "Data ${listItem[position].name} Gagal dihapus", Toast.LENGTH_LONG)
                    }
                }
                (holder.itemView.context as MainActivity).fetchData()
            }

        }

        holder.itemView.btnEdit.setOnClickListener{
            val intentGoToEdit = Intent(it.context, EditActivity::class.java)
            intentGoToEdit.putExtra("item", listItem[position])

            it.context.startActivity(intentGoToEdit)

        }
    }


}