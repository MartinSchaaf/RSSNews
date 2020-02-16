package com.example.rssnews.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rssnews.R
import com.example.rssnews.view_model.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }


    override fun onStart() {
        super.onStart()

        //Clear data for CurrentNewsItemFragment View Model

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


    companion object{

        fun hideBackButtonFromToolbar(activity: MainActivity){

            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            activity.supportActionBar?.setDisplayShowHomeEnabled(false)
        }

        fun showBackButtonInToolbar(activity: MainActivity){

            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        }


    }
}
