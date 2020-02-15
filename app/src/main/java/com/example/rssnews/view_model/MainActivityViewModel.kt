package com.example.rssnews.view_model

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){

    val onListItemClickLiveData: MutableLiveData<Bundle> = MutableLiveData()
}