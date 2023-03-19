package com.example.education

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.asmenys_layout.view.*

class Adapter (private val context: Context, private val items:ArrayList<AsmenysModel>): RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.asmenys_layout,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item = items[position]
        holder.vardas.text = item.vardas
        holder.amzius.text = item.amzius.toString()
        holder.lytis.text = item.lytis
        holder.pomegiai.text = item.pomegiai.toString().replace("[", "").replace("]", "")
        println("pomegiaiAdapter" + item.pomegiai.toString())

    }
    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val vardas = view.vardas
        val amzius = view.amzius
        val lytis = view.lytis
        val pomegiai = view.pomegiai
    }
}


