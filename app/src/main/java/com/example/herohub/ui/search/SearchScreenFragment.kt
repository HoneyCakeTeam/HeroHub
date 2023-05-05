package com.example.herohub.ui.search


import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSearchScreenBinding
import com.example.herohub.ui.base.BaseFragment

class SearchScreenFragment  : BaseFragment<FragmentSearchScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_search_screen
    override val viewModel: SearchViewModel by viewModels()

    override fun setup() {
        viewModel.getAllCharacters()
        initAdapter()
    }

    private fun initAdapter(){
        val adapter = SearchAdapter(viewModel)
        binding.rvSearchResult.adapter = adapter
    }
}