package com.example.herohub.ui.home

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentHomeBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.home.adapter.HomeAdapter
import com.example.herohub.ui.home.adapter.SuperHeroesAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val TAG: String = this::class.java.simpleName.toString()
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    lateinit var homeAdapter: HomeAdapter

    override fun setup() {
     initHomeAdapter()
        viewModel.characterResponse.observe(this){
            val adapter = SuperHeroesAdapter(it.toData().results)
        }
    }
    private fun initHomeAdapter(){
        val list = mutableListOf(HomeItem.Slider(emptyList()), HomeItem.SuperHeroes(emptyList()), HomeItem.MostPopularComics(
            emptyList()
        ))
        homeAdapter = HomeAdapter(list, viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }
}