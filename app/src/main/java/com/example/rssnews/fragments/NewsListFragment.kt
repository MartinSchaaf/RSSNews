package com.example.rssnews.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.rssnews.R
import com.example.rssnews.model.NewsListRecyclerViewAdapter
import com.example.rssnews.model.VestiAPIService
import com.example.rssnews.view_model.NewsListFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewsListFragment : Fragment() {

    lateinit var viewModel: NewsListFragmentViewModel
    lateinit var recyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewsListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        recyclerView = view.findViewById(R.id.news_list_recyclerView)

        return view
    }

    override fun onStart() {
        super.onStart()

        CoroutineScope(IO).launch {
            val response = VestiAPIService.vestiAPIService.getNews()
            viewModel.newsResponse.postValue(response)

            withContext(Main){

                recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                recyclerView.adapter = NewsListRecyclerViewAdapter(viewModel.newsResponse.value?.channel?.item!!)
            }
        }


        viewModel.newsResponse.observe(this, Observer {

        })
    }




}
