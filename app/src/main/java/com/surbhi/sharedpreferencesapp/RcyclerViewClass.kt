package com.surbhi.sharedpreferencesapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewClass(var number : Int, var color1: String, var color2 : String) : RecyclerView.Adapter<RecyclerViewClass.TextViewHolder>(){
    class TextViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewClass.TextViewHolder {
        return TextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent,false))

    }


    override fun getItemCount(): Int {
      return number
    }

    override fun onBindViewHolder(holder: RecyclerViewClass.TextViewHolder, position: Int) {
        if(position % 2 == 0){
            holder.name.setBackgroundColor(Color.parseColor(color1))
        }else{
            holder.name.setBackgroundColor(Color.parseColor(color2))
        }
    }

}