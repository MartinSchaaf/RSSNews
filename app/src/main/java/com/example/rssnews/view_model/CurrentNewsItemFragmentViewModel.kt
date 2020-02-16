package com.example.rssnews.view_model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class CurrentNewsItemFragmentViewModel : ViewModel() {

    val imageURL: ObservableField<String> = ObservableField()

    val title: ObservableField<String> = ObservableField()

    val fullText: ObservableField<String> = ObservableField()
}