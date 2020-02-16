package com.example.rssnews.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rssnews.R
import com.example.rssnews.databinding.NewsListItemRowBinding
import com.example.rssnews.model.room.NewsItem

class NewsListRecyclerViewAdapter(
    var data: List<NewsItem>?,
    private val onClickLivaData: MutableLiveData<Bundle>

) : RecyclerView.Adapter<NewsListRecyclerViewAdapter.NewsListViewHolder>() {

    interface OnCompleteUpdateRecyclerView {

        fun onCompleteUpdateRecyclerView()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<NewsListItemRowBinding>(
            inflater,
            R.layout.news_list_item_row,
            parent,
            false
        )
        return NewsListViewHolder(binding)
    }

    override fun getItemCount(): Int = if (data != null) data!!.size else 0

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {

        data?.let {
            holder.bind(data!![position])

            holder.binding.root.setOnClickListener {

                val item = holder.binding.item

                val bundle = Bundle()
                bundle.putString("title", item!!.title)
                bundle.putString("imageUrl", item.imageUrl)
                bundle.putString("fullText", item.fullText)

                onClickLivaData.value = bundle
            }
        }


    }

    fun updateData(newData: List<NewsItem>, listener: OnCompleteUpdateRecyclerView) {

        val diffUtilCallback = NewsListDiffUtil(newData = newData, oldData = data)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        data = newData
        diffResult.dispatchUpdatesTo(this)
        listener.onCompleteUpdateRecyclerView()
    }

    inner class NewsListViewHolder(val binding: NewsListItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsItem?) {

            binding.item = item!!
        }

    }

}