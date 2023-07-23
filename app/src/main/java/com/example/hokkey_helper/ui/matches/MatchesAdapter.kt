package com.example.hokkey_helper.ui.matches

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hokkey_helper.R
import com.example.hokkey_helper.databinding.MatchItemBinding
import com.example.hokkey_helper.model.MatchData

class MatchesAdapter() : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    var data: List<MatchData> = listOf()
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.firstCountry.text = item.countries.first.name
        holder.binding.secondCountry.text = item.countries.second.name
        Glide.with(holder.itemView.context)
            .load(item.commands.first.imageURL)
            .fitCenter()
            .placeholder(R.drawable.no_img)
            .error(R.drawable.no_img)
            .into(holder.binding.firstImage)

        Glide.with(holder.itemView.context)
            .load(item.commands.second.imageURL)
            .fitCenter()
            .placeholder(R.drawable.no_img)
            .error(R.drawable.no_img)
            .into(holder.binding.secondImage)

        if (item.isBlocked){
            holder.binding.blockedWindow.visibility = View.VISIBLE
            holder.binding.price.text = item.priceList.map { it.price }.sum().toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = MatchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(dataList: List<MatchData>){
        data = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MatchItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

}