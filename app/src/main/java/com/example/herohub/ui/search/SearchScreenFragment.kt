package com.example.herohub.ui.search


import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSearchScreenBinding
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseFragment

class SearchScreenFragment  : BaseFragment<FragmentSearchScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_search_screen
    override val viewModel: SearchViewModel by viewModels()

    override fun setup() {
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = SearchAdapter(viewModel)
        binding.rvSearchResult.adapter = adapter
        viewModel.getSearchResult()
        }
    }


//        binding.searchView.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.setSearchQuery(s.toString())
//            }
//
//            override fun afterTextChanged(s: Editable?) {}
//        })