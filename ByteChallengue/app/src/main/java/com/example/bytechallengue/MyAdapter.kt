package com.example.bytechallengue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bytechallengue.api.MyDataItem

class MyAdapter (val context: Context, val dataList: List<MyDataItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val donutId: TextView
        val donutName: TextView
        val donutType: TextView

        init{
            donutId = itemView.findViewById<TextView>(R.id.tvId)
            donutName = itemView.findViewById<TextView>(R.id.tvName)
            donutType = itemView.findViewById<TextView>(R.id.tvType)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.donutId.text = dataList[position].id.toString()
        holder.donutName.text = dataList[position].name
        holder.donutType.text = dataList[position].type

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}