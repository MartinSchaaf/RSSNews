package com.example.rssnews.model

import androidx.recyclerview.widget.DiffUtil
import com.example.rssnews.model.room.NewsItem

class NewsListDiffUtil(val oldData: List<NewsItem>?, val newData: List<NewsItem>?)  : DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldData?.size ?: 0

    override fun getNewListSize(): Int = newData?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return if (newListSize!=0 && oldListSize!=0){

            oldData!![oldItemPosition].link == newData!![newItemPosition].link

        } else false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (newListSize!=0 && oldListSize!=0){

            return oldData!![oldItemPosition].title == newData!![newItemPosition].title &&
                    oldData[oldItemPosition].fullText == newData[newItemPosition].fullText

        } else false

    }

}