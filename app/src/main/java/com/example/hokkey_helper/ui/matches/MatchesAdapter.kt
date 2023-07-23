package com.example.hokkey_helper.ui.matches

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hokkey_helper.R
import com.example.hokkey_helper.databinding.MatchItemBinding
import com.example.hokkey_helper.model.MatchData
import com.example.hokkey_helper.model.SaleData

class MatchesAdapter(
    val rewritePermission:(saleList: List<SaleData>)-> Unit = {}

) : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    var data: List<MatchData> = listOf()
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.firstCountry.text = item.countries.first.name
        holder.binding.secondCountry.text = item.countries.second.name
        holder.binding.command1.text = item.commands.first.name
        holder.binding.command2.text = item.commands.second.name
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
            holder.binding.price.text = item.priceList.sumOf { it.price }.toString()
        }else{
            holder.binding.blockedWindow.visibility = View.GONE
        }

    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        val item = data[holder.adapterPosition]
        holder.binding.main.setOnClickListener {
            if (item.isBlocked){
                BuyMatchDialogFragment.newInstance(
                    item
                ) {
                    rewritePermission(item.priceList)
                }.show(
                    (holder.itemView.context as AppCompatActivity).supportFragmentManager,
                    BuyMatchDialogFragment.tag
                )
            }else{
                holder.binding.root.findNavController().navigate(R.id.match_detail, bundleOf("item_id" to item.id))
            }
        }
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.binding.main.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    fun refresh(dataList: List<MatchData>){
        data = dataList.map{it.copy()}
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