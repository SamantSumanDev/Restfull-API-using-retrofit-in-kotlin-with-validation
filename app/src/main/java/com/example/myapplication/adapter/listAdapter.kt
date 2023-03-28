package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Data
import com.example.myapplication.model.ListDataResponse
import com.example.myapplication.model.ListdataRes2

class listAdapter<T>( private var mList: List<Data>) :
    RecyclerView.Adapter<listAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.txtId.text = ItemsViewModel.id.toString()
        holder.txtFirstName.text = ItemsViewModel.first_name
        holder.txtLastName.text = ItemsViewModel.last_name

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtId)
        val txtFirstName: TextView = itemView.findViewById(R.id.txtFirstName)
        val txtLastName: TextView = itemView.findViewById(R.id.txtLastName)

    }

    fun setData(data: List<Data>) {
        mList = data
        notifyDataSetChanged()
    }
}
