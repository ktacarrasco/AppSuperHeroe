package com.example.myappsuperheroe.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myappsuperheroe.R
import com.example.myappsuperheroe.pojo.SuperHeroe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photos.view.*

class AdapterSH  (var mdataSetP: List<SuperHeroe>): RecyclerView.Adapter<AdapterSH.photoHolder>(){

    fun updateData(listSH: List<SuperHeroe>) {
        Log.d("UPDATE", "update ${listSH.size}")
        mdataSetP = listSH
        notifyDataSetChanged()
    }

        class photoHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val titleTv= itemView.titleTV
            val photoTv= itemView.photoTV
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): photoHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_photos, parent, false)
            return photoHolder(view)
        }

        override fun getItemCount(): Int {
            Log.d("Cantidad",mdataSetP.size.toString())
            return mdataSetP.size
        }

        override fun onBindViewHolder(holder: photoHolder, position: Int) {
            val photo =  mdataSetP[position]

            val titletv ="Super Hero: ${photo.name}"
            //val phototv="images: ${Images.sm}"

            holder.titleTv.text = titletv

            Picasso.get()
                .load(photo.images.lg)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.photoTv)

             /* Picasso.get()
                   .load(mdataSetP[position].images)
                   .into(holder.photoTv)*/

            //holder.photoTv.te = email

        }


    }
