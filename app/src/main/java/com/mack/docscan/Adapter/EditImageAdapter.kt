package com.mack.docscan.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mack.docscan.R

class EditImageAdapter( private val imageUris :List<String>): RecyclerView.Adapter<EditImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.IV_ImageShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(R.layout.image_layout_show_image,parent,false)
        return ImageViewHolder(viewLayout)
    }

    override fun getItemCount(): Int {
         return imageUris.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val uri = imageUris[position]
        Glide.with(holder.itemView.context)
            .load(uri)
            .into(holder.imageView)
    }
}