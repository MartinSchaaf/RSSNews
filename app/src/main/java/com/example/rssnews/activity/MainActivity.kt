package com.example.rssnews.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import com.example.rssnews.R
import com.example.rssnews.fragments.NewsListFragment
import com.example.rssnews.model.VestiAPIService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        val fragment = NewsListFragment()
        showFragment(fragment)

    }

    fun showFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(container.id,fragment).commit()
    }
}
