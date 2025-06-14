package com.example.waste2cash.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waste2cash.Model.PickupRequest
import com.example.waste2cash.R

class PickupRequestAdapter(private val pickupRequests: List<PickupRequest>) :
    RecyclerView.Adapter<PickupRequestAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBarang: TextView = itemView.findViewById(R.id.tv_barang)
        val tvNama: TextView = itemView.findViewById(R.id.tv_nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tv_alamat)
        val tvNomorTelp: TextView = itemView.findViewById(R.id.tv_nomor_telp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.request_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = pickupRequests[position]
        holder.tvBarang.text = "${currentItem.categoryName}: ${currentItem.weight}kg"
        holder.tvNama.text = currentItem.userName
        holder.tvAlamat.text = currentItem.userAddress ?: "Alamat tidak tersedia"
        holder.tvNomorTelp.text = currentItem.userPhoneNumber ?: "Nomor telepon tidak tersedia"
    }

    override fun getItemCount(): Int {
        return pickupRequests.size
    }
}