package com.example.panelsurya

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import java.io.IOException
import java.util.*
import java.lang.Float
import kotlin.collections.ArrayList
import kotlin.coroutines.coroutineContext

class MyAdapter(private val sapiList: ArrayList<solar>
                 ):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

//    private lateinit var mListener : FirebaseDataListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = sapiList[position]

        holder.time.text = currentItem.time
        holder.volt.text = currentItem.vin
        holder.kemiringan.text = currentItem.pos
        holder.hujan.text = currentItem.nilairain
        holder.ldrDepan.text = currentItem.ldrd
        holder.ldrBelakang.text = currentItem.ldrb

    }

    class MyViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        val time:TextView = itemView.findViewById(R.id.tvTimeHistory)
        val volt:TextView = itemView.findViewById(R.id.tvVoltHistory)
        val kemiringan:TextView = itemView.findViewById(R.id.tvKemiringanHistory)
        val hujan:TextView = itemView.findViewById(R.id.tvHujanHistory)
        val ldrDepan:TextView = itemView.findViewById(R.id.tvLdrDepanHistory)
        val ldrBelakang:TextView = itemView.findViewById(R.id.tvLdrBelakangHistory)

//        init{
//            itemView.setOnClickListener{
//                clickListener.onItemClick(adapterPosition)
//            }
//        }

    }

    override fun getItemCount(): Int {
        return sapiList.size
    }

}