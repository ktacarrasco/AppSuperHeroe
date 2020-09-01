package com.example.myappsuperheroe.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myappsuperheroe.R
import com.example.myappsuperheroe.pojo.Images
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photos.view.*

class AdapterImages (var mdataSet: List<Images>): RecyclerView.Adapter<AdapterImages.imagenHolder>() {

    class imagenHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        //val titleTv= itemView.titleTV
        val photoTv= itemView.photoTV
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imagenHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_photos, parent, false)
        return AdapterImages.imagenHolder(view)
    }

    override fun getItemCount(): Int {
        return mdataSet.size
    }

    override fun onBindViewHolder(holder: imagenHolder, position: Int) {
        val photo =  mdataSet[position]

       // val titletv ="Title: ${photo.name}"
      //  val phototv="images: ${Images.}"

       //holder.titleTv.text = titletv
           Picasso.get()
               .load(mdataSet[position].sm)
               .into(holder.photoTv)

        //holder.photoTv.te = email
    }


}