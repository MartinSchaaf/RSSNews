package com.example.rssnews.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rssnews.R
import com.example.rssnews.activity.MainActivity
import com.example.rssnews.databinding.FragmentCurrentNewsItemBinding
import com.example.rssnews.view_model.CurrentNewsItemFragmentViewModel
import com.example.rssnews.view_model.MainActivityViewModel


class CurrentNewsItemFragment : Fragment() {

    private lateinit var binding: FragmentCurrentNewsItemBinding
    private lateinit var viewModel: CurrentNewsItemFragmentViewModel
    private lateinit var activityViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CurrentNewsItemFragmentViewModel::class.java)
        activityViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_current_news_item,null,false)
        binding.vmodel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        activityViewModel.onListItemClickLiveData.observe(this, Observer {

            it?.let {

                viewModel.title.set(it.getString("title"))
                viewModel.imageURL.set(it.getString("imageUrl"))
                viewModel.fullText.set(it.getString("fullText"))
            }

        })

        MainActivity.showBackButtonInToolbar(activity as MainActivity)
    }

}
