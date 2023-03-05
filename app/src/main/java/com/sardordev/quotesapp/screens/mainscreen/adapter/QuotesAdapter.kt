package com.sardordev.quotesapp.screens.mainscreen.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sardordev.quotesapp.R
import com.sardordev.quotesapp.data.model.AllTopicsResult
import com.sardordev.quotesapp.databinding.ItemQuotesCardBinding
import com.sardordev.quotesapp.utils.ClickItemListener
import kotlin.random.Random

class QuotesAdapter(private val listener: ClickItemListener) :
    ListAdapter<AllTopicsResult, QuotesAdapter.VH>(diff) {


    inner class VH(val binding: ItemQuotesCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(allTopicsResult: AllTopicsResult) {
            binding.tvTopicName.text = allTopicsResult.name

            itemView.setOnClickListener {
                listener.clickItem(allTopicsResult)
            }



        }

    }

    object diff : DiffUtil.ItemCallback<AllTopicsResult>() {
        override fun areItemsTheSame(oldItem: AllTopicsResult, newItem: AllTopicsResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AllTopicsResult,
            newItem: AllTopicsResult
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemQuotesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onbind(getItem(position))
        holder.binding.cardView.setCardBackgroundColor(
            holder.itemView.resources.getColor(
                randomColor(),
                null
            )
        )

    }

    fun randomColor(): Int {
        val list = ArrayList<Int>()

        list.add(R.color.c1)
        list.add(R.color.c2)
        list.add(R.color.c3)
        list.add(R.color.c4)
        list.add(R.color.c5)
        list.add(R.color.c6)
        list.add(R.color.c7)
        list.add(R.color.c8)



        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

}