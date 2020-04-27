package com.lefebvre.thomas.newordomanager.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lefebvre.thomas.newordomanager.database.Ordonnance
import com.lefebvre.thomas.newordomanager.databinding.ListItemOrdoBinding


class OrdoAdapter(val clickListener: OrdoListener): ListAdapter<Ordonnance, OrdoAdapter.ViewHolder>(OrdoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position),clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemOrdoBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Ordonnance,clickListener: OrdoListener) {
            binding.ordonnance = item
            binding.listener=clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemOrdoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class OrdoDiffCallback : DiffUtil.ItemCallback<Ordonnance>() {

    override fun areItemsTheSame(oldItem: Ordonnance, newItem: Ordonnance): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Ordonnance, newItem: Ordonnance): Boolean {
        return oldItem == newItem
    }


}


class OrdoListener(val clickListener: (commentId: Ordonnance) -> Unit) {
    fun onClick(ordo: Ordonnance) = clickListener(ordo)
}

