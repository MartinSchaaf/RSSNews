package com.example.rssnews.view_model

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rssnews.model.room.NewsItem

class NewsListFragmentViewModel : ViewModel() {

    val newsItemsList:MutableLiveData<List<NewsItem>> = MutableLiveData()

    val categoriesList:ObservableField<List<String>> = ObservableField()
}