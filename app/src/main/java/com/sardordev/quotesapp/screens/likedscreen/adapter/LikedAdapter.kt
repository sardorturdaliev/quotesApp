package com.sardordev.quotesapp.screens.likedscreen.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sardordev.quotesapp.data.entity.LikeEntity
import com.sardordev.quotesapp.data.model.ResultAlone
import com.sardordev.quotesapp.databinding.ItemCardBinding
import com.sardordev.quotesapp.screens.mainscreen.adapter.QuotesAdapter
import kotlin.random.Random


class LikedAdapter : ListAdapter<LikeEntity, LikedAdapter.Vh>(diff) {
    private var clickcopy: ((LikeEntity) -> Unit)? = null
    private var clicklike: ((LikeEntity) -> Unit)? = null

    inner class Vh(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onbind(likeEntity: LikeEntity) {
            binding.tvQuotesByAuthors.text = likeEntity.quote
            binding.tvauthorName.text = likeEntity.authorName
            binding.imgcopy.setOnClickListener {
                clickcopy?.invoke(likeEntity)
            }
//
            binding.imgliked.setOnClickListener {
                clicklike?.invoke(likeEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Vh(
        ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onbind(getItem(position))
    }


    object diff : DiffUtil.ItemCallback<LikeEntity>() {
        override fun areItemsTheSame(oldItem: LikeEntity, newItem: LikeEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LikeEntity, newItem: LikeEntity): Boolean {
            return oldItem == newItem
        }
    }


    fun setClickCopy(block: (LikeEntity) -> Unit) {
        clickcopy = block
    }

    fun setClickLike(block : (LikeEntity) -> Unit){
        clicklike = block
    }

}