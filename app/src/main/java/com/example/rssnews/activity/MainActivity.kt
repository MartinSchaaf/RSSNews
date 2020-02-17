package com.example.rssnews.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rssnews.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }


    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

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
