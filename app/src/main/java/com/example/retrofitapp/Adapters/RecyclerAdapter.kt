package com.example.retrofitapp.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.MainActivity
import com.example.retrofitapp.R
import com.example.retrofitapp.Retrofit.PosDto

class RecyclerAdapter(private var posts : List<PosDto> )   : RecyclerView.Adapter< RecyclerAdapter.myViewHolder>() {

     var activity:MainActivity?=null


        class   myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var postheader: TextView = itemView.findViewById(R.id.layout_posttitle)

            var postbody: TextView = itemView.findViewById(R.id.layout_postbody)




}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter. myViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view, parent, false)
        return  myViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerAdapter. myViewHolder, position: Int) {
        val item = posts[position]
        holder. postheader.text = item.title
        var body=item.body
        if(body.length>70){
            body=body.substring(0,70)+"..."
        }
        holder.postbody.text =  body
        holder.itemView.setOnClickListener {


            activity?.click(item.id)


        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setactivity(activity: MainActivity){
        this.activity=activity

    }





}