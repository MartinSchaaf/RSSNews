package com.example.rssnews.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rssnews.R
import com.example.rssnews.fragments.CurrentNewsItemFragment
import com.example.rssnews.fragments.NewsListFragment
import com.example.rssnews.model.VestiAPIService
import com.example.rssnews.view_model.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }

    override fun onStart() {
        super.onStart()

        viewModel.onListItemClickLiveData.value = null

        viewModel.onListItemClickLiveData.observe(this, Observer {

            it?.let {

                navController.navigate(R.id.currentNewsItemFragment)
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {

        supportFragmentManager.popBackStack()

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        return true
    }

}
