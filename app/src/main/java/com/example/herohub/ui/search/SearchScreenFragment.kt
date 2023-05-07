package com.example.herohub.ui.search


import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSearchScreenBinding
import com.example.herohub.ui.base.BaseFragment

class SearchScreenFragment  : BaseFragment<FragmentSearchScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_search_screen
    override val viewModel: SearchViewModel by viewModels()

    override fun setup() {
        initAdapter()
        getSearchResult()
    }

    private fun getSearchResult(){
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }
        })
    }

    private fun initAdapter(){
        val adapter = SearchAdapter(viewModel)
        binding.rvSearchResult.adapter = adapter
        /*viewModel.searchResult.observe(viewLifecycleOwner){
            adapter.setItems(it)
        }*/
    }
}