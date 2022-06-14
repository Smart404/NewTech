package com.lee.androidnewtech.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lee.androidnewtech.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {

    private val colorList = ArrayList<Int>()

    init {
        colorList.add(R.color.blue)
        colorList.add(R.color.red)
        colorList.add(R.color.green)
        colorList.add(R.color.purple_200)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mTvTitle?.text = "item$position"
        holder.itemView.setBackgroundResource(colorList[position % 4])
    }

    override fun getItemCount(): Int = 10

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTvTitle: TextView? = itemView.findViewById(R.id.tvTitle)
    }
}