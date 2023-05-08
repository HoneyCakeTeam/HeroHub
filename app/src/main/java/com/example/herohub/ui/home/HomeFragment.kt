package com.example.herohub.ui.home

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentHomeBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.home.adapter.HomeAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val TAG: String = "TAG"
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter


    override fun setup() {
        initHomeAdapter()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
        viewModel.homeItemsLiveData.observe(this) {
            Log.e(TAG, "initHomeAdapter: $it")
            homeAdapter.setItems(it)
        }
    }


}