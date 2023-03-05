package com.sardordev.quotesapp.screens.detailscreen.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sardordev.quotesapp.data.model.ResultAlone
import com.sardordev.quotesapp.databinding.ItemCardBinding
import com.sardordev.quotesapp.screens.mainscreen.adapter.QuotesAdapter
import kotlin.random.Random

class DetailAdapter : ListAdapter<ResultAlone, DetailAdapter.Vh>(diff) {
    private var clickcopy: ((ResultAlone) -> Unit)? = null
    private var clicklike: ((ResultAlone) -> Unit)? = null


    inner class Vh(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(resultAlone: ResultAlone) {
            binding.tvQuotesByAuthors.text = resultAlone.quote
            binding.tvauthorName.text = resultAlone.name


            binding.imgcopy.setOnClickListener {
                clickcopy?.invoke(resultAlone)
            }

            binding.imgliked.setOnClickListener {
                clicklike?.invoke(resultAlone)
            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Vh(
        ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onbind(getItem(position))


    }


    object diff : DiffUtil.ItemCallback<ResultAlone>() {
        override fun areItemsTheSame(oldItem: ResultAlone, newItem: ResultAlone): Boolean {
            return oldItem.quoteId == newItem.quoteId
        }

        override fun areContentsTheSame(oldItem: ResultAlone, newItem: ResultAlone): Boolean {
            return oldItem == newItem
        }
    }


    fun setClickCopy(block: (ResultAlone) -> Unit) {
        clickcopy = block
    }

    fun setClickLike(block : (ResultAlone) -> Unit){
        clicklike = block
    }

}