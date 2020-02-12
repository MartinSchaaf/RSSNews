package com.example.rssnews.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rssnews.R
import com.example.rssnews.databinding.NewsListItemRowBinding
import com.example.rssnews.model.pojo.NewsItem

class NewsListRecyclerViewAdapter(var data: List<NewsItem>) : RecyclerView.Adapter<NewsListRecyclerViewAdapter.NewsListViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<NewsListItemRowBinding>(inflater, R.layout.news_list_item_row, parent, false)
        return NewsListViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {

        holder.bind(data[position])
    }

    inner class NewsListViewHolder(val binding: NewsListItemRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: NewsItem?){

            binding.item = item!!
        }

    }

}