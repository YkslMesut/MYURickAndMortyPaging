package com.myu.myurickandmortypaging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.myu.myurickandmortypaging.databinding.CharacterLayoutBinding
import com.myu.myurickandmortypaging.model.Result

class RickMortyPagerAdapter : PagingDataAdapter<Result,RickMortyPagerAdapter.MyViewHolder>(diffCallback) {

    inner class MyViewHolder(
        val binding : CharacterLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem  = getItem(position)

        holder.binding.apply {
            tvDescription.text = currentItem?.name

            imageView.load(currentItem?.image) {
                crossfade(true)
                crossfade(1000)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CharacterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }
}