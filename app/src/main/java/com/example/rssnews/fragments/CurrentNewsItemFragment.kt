package com.example.rssnews.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.rssnews.R
import com.example.rssnews.activity.MainActivity
import com.example.rssnews.databinding.FragmentCurrentNewsItemBinding
import com.example.rssnews.view_model.CurrentNewsItemFragmentViewModel


class CurrentNewsItemFragment : Fragment() {

    private lateinit var binding: FragmentCurrentNewsItemBinding
    private lateinit var viewModel: CurrentNewsItemFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CurrentNewsItemFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_current_news_item, null, false)
        binding.vmodel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.title.set(arguments?.getString("title"))
        viewModel.imageURL.set(arguments?.getString("imageUrl"))
        viewModel.fullText.set(arguments?.getString("fullText"))


        MainActivity.showBackButtonInToolbar(activity as MainActivity)
    }

}
