package com.example.rssnews.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rssnews.model.pojo.Rss

class NewsListFragmentViewModel : ViewModel() {

    val newsResponse:MutableLiveData<Rss> = MutableLiveData()
}