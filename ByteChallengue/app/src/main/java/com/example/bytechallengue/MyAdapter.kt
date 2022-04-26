package com.example.bytechallengue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bytechallengue.api.MyDataItem
import com.example.bytechallengue.databinding.RowItemsBinding

class MyAdapter(val context: Context, val dataList: List<MyDataItem>, private var listener: MainActivity): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val donutId: TextView
        val donutName: TextView
        val donutType: TextView

        init {
            donutId = itemView.findViewById<TextView>(R.id.tvId)
            donutName = itemView.findViewById<TextView>(R.id.tvName)
            donutType = itemView.findViewById<TextView>(R.id.tvType)
        }

        val mBinding = RowItemsBinding.bind(itemView)

        fun setListener(myDataItem: MyDataItem){
            mBinding.root.setOnClickListener {
                listener.onClick(myDataItem.id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = dataList.get(position)
        with(holder) {
            setListener(store)
            donutId.text = dataList[position].id.toString()
            donutName.text = dataList[position].name
            donutType.text = dataList[position].type
        }
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

}