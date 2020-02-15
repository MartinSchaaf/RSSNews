package com.example.rssnews.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.rssnews.R
import com.example.rssnews.model.NewsListRecyclerViewAdapter
import com.example.rssnews.model.VestiAPIService
import com.example.rssnews.model.room.DataBaseDao
import com.example.rssnews.view_model.NewsListFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.SocketTimeoutException
import androidx.room.Room
import com.example.rssnews.model.room.AppDatabase
import com.example.rssnews.model.room.NewsItem
import android.widget.ArrayAdapter
import com.example.rssnews.activity.MainActivity
import com.example.rssnews.model.UserSharedPreferences
import com.example.rssnews.view_model.MainActivityViewModel
import java.io.IOException
import java.util.ArrayList


class NewsListFragment : Fragment() {

    lateinit var viewModel: NewsListFragmentViewModel
    lateinit var activityViewModel: MainActivityViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var categorySpinner: Spinner
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var recyclerViewAdapter: NewsListRecyclerViewAdapter
    lateinit var ctx: Context
    lateinit var dao: DataBaseDao


    override fun onAttach(context: Context) {
        super.onAttach(context)

        ctx = context
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewsListFragmentViewModel::class.java)
        activityViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        dao =
            Room.databaseBuilder(ctx, AppDatabase::class.java, "response_database").build().getDao()

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

        recyclerViewAdapter =
            NewsListRecyclerViewAdapter(null, activityViewModel.onListItemClickLiveData)
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter = recyclerViewAdapter


        loadData()

        viewModel.newsItemsList.observe(this, Observer {

            recyclerViewAdapter.updateData(it,
                object : NewsListRecyclerViewAdapter.OnCompleteUpdateRecyclerView {

                    override fun onCompleteUpdateRecyclerView() {
                        swipeRefresh.isRefreshing = false
                    }
                })

            val categories = LinkedHashSet<String>()

            it.forEach { newsItem ->

                categories.add(newsItem.category)
            }

            categories.add("Все")

            viewModel.categoriesList.set(categories.toList())


            setCategorySpinnerAdapter()
        })



        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(ctx, R.color.colorAccent))

        swipeRefresh.setOnRefreshListener {
            loadData()
        }

    }


    private fun loadData() {

        CoroutineScope(IO).launch {

            try {

                if (!swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = true

                val response = VestiAPIService.vestiAPIService.getNews()

                //Add categories list to ViewModel

                val categories = LinkedHashSet<String>()

                response.channel?.item?.forEach { newsItem ->

                    categories.add(newsItem.category!!)
                }

                categories.add("Все")

                viewModel.categoriesList.set(categories.toList())


                //Add news items to database and viewModel

                val itemsListFromResponse: MutableList<NewsItem> = mutableListOf()
                val itemsListFromDataBase = dao.getAll()

                itemsListFromDataBase?.forEach {
                    dao.delete(it)
                }


                response.channel?.item?.forEach { it ->

                    val newsItem = NewsItem(

                        if (it.enclosure != null) it.enclosure[0].url!!
                        else "no url",
                        it.title!!,
                        it.description!!,
                        it.fullText!!,
                        it.pubDate!!,
                        it.link!!,
                        it.category!!
                    )

                    itemsListFromResponse.add(newsItem)

                    dao.insert(newsItem)
                }

                viewModel.newsItemsList.postValue(itemsListFromResponse)

            } catch (e: SocketTimeoutException) {


                e.printStackTrace()

                if (dao.getAll() != null) {
                    viewModel.newsItemsList.postValue(dao.getAll())
                    withContext(Main) { showToast("Превышено время ожидания, загружены предыдущие данные") }

                } else withContext(Main) { showToast("Превышено время ожидания") }


            } catch (e: Exception) {


                e.printStackTrace()

                if (dao.getAll() != null) {
                    viewModel.newsItemsList.postValue(dao.getAll())
                    withContext(Main) { showToast("Ошибка соединения, загружены предыдущие данные") }

                } else withContext(Main) { showToast("Ошибка соединения") }


            } finally {

                withContext(Main) { swipeRefresh.isRefreshing = false }
            }
        }
    }


    private fun showToast(message: String) {

        Toast.makeText(
            ctx,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun setCategorySpinnerAdapter() {

        val adapter =
            ArrayAdapter<String>(
                ctx,
                android.R.layout.simple_spinner_item,
                viewModel.categoriesList.get()!!
            )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        var id = 0

        viewModel.categoriesList.get()!!.forEachIndexed { index, s ->

            if (s == UserSharedPreferences.getLastUserSelectedCategory())
                id = index
        }

        categorySpinner.setSelection(id)

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                swipeRefresh.isRefreshing = true

                val category = viewModel.categoriesList.get()!![position]
                UserSharedPreferences.setLastUserSelectedCategory(category)
                filterByCategory()
            }

        }
    }


    private fun filterByCategory() {

        val category = UserSharedPreferences.getLastUserSelectedCategory()

        var sortedItemsList: MutableList<NewsItem> = mutableListOf()

        CoroutineScope(IO).launch {

            if (category != "Все") {

                dao.getAll()?.forEach {

                    if (it.category == category) sortedItemsList.add(it)
                }
            } else sortedItemsList = dao.getAll()!!.toMutableList()


            withContext(Main) {

                recyclerViewAdapter.updateData(sortedItemsList,
                    object : NewsListRecyclerViewAdapter.OnCompleteUpdateRecyclerView {

                        override fun onCompleteUpdateRecyclerView() {
                            swipeRefresh.isRefreshing = false
                        }
                    })
            }

        }

    }

}
