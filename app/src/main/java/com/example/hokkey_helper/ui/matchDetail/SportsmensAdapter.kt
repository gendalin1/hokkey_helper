package com.example.hokkey_helper.ui.matchDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hokkey_helper.databinding.SportsmenItemBinding
import com.example.hokkey_helper.model.Sportsmen


class SportsmensAdapter() : RecyclerView.Adapter<SportsmensAdapter.ViewHolder>() {

    var data: List<Pair<Sportsmen,String>> = listOf()
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.sportsmenName.text = item.first.name
        holder.binding.sportsmenDuty.text = item.second
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    fun refresh(_data: List<Pair<Sportsmen,String>>){
        data = _data
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: SportsmenItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SportsmenItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }
}