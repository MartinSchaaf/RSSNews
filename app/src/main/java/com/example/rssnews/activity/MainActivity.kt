package com.example.rssnews.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rssnews.R
import com.example.rssnews.model.VestiAPIService
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

CoroutineScope(Main).launch {
    println(VestiAPIService.vestiAPIService.getNews())
}




    }
}
