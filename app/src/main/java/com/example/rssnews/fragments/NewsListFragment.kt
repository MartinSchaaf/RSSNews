package com.example.rssnews.fragments

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.rssnews.R
import com.example.rssnews.activity.MainActivity
import com.example.rssnews.model.NewsListRecyclerViewAdapter
import com.example.rssnews.model.VestiAPIService
import com.example.rssnews.view_model.NewsListFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.SocketTimeoutException


class NewsListFragment : Fragment() {

    lateinit var viewModel: NewsListFragmentViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var categorySpinner: Spinner
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var adapter: NewsListRecyclerViewAdapter
    lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)

        ctx = context
    }


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
        categorySpinner = view.findViewById(R.id.category_spinner)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)

        return view
    }


    override fun onStart() {
        super.onStart()

        adapter = NewsListRecyclerViewAdapter(null)
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter = adapter

        if (viewModel.newsResponse.value == null){

            loadData()
        }

        viewModel.newsResponse.observe(this, Observer {

            adapter.updateData(it.channel?.item!!,
                object : NewsListRecyclerViewAdapter.OnCompleteUpdateRecyclerView {

                    override fun onCompleteUpdateRecyclerView() {
                        swipeRefresh.isRefreshing = false
                    }
                })
        })


        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(ctx,R.color.colorAccent))

        swipeRefresh.setOnRefreshListener {
            loadData()
        }

    }


    private fun loadData() {

        CoroutineScope(IO).launch {

            try {

                if (!swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = true

                val response = VestiAPIService.vestiAPIService.getNews()
                viewModel.newsResponse.postValue(response)

                val categories = LinkedHashSet<String>()

                viewModel.newsResponse.value!!.channel?.item?.forEach { newsItem ->

                    categories.add(newsItem.category!!)
                }

                viewModel.categotiesList.set(categories.toList())

            } catch (e: SocketTimeoutException) {

                e.printStackTrace()
                withContext(Main) {

                    showToast("Превышено время ожидания")
                }

            } catch (e:Exception){

                e.printStackTrace()
                withContext(Main) {

                    showToast("Ошибка соединения")
                }

            } finally {

                withContext(Main){
                    swipeRefresh.isRefreshing = false
                }
            }
        }
    }


    private fun showToast(message: String){
        Toast.makeText(
            ctx,
            message,
            Toast.LENGTH_LONG
        ).show()
    }


}
