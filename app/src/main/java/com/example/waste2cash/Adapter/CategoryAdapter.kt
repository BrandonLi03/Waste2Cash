package com.example.waste2cash.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.waste2cash.Model.Category
import com.example.waste2cash.R

class CategoryAdapter(private val categoryList: List<Category>, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<CategoryAdapter.ViewHolderClass>(){

    interface OnItemClickListener {
        fun onItemClick(item: Category)
    }

    class ViewHolderClass(itemView: View, private val itemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private var categoryName = itemView.findViewById<TextView>(R.id.tv)
        private var image = itemView.findViewById<ImageView>(R.id.img)
        private var card = itemView.findViewById<View>(R.id.category_layout)

        fun bind(category: Category) {
            categoryName.text = category.categoryName

            Glide.with(itemView.context).load(category.categoryImg).into(image)

            card.setOnClickListener {
                itemClickListener.onItemClick(category)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_card_layout, parent, false)
        return ViewHolderClass(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolderClass, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return if (categoryList.size > 4) 4 else categoryList.size
    }
}
