package com.example.herohub.ui.search


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSearchScreenBinding
import com.example.herohub.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_search_screen
    override val viewModel: SearchViewModel by viewModels()

    override fun setup() {
        initAdapter()
        viewModel.navigateToItem.observe(viewLifecycleOwner) {
            if (viewModel.isNavigated.value == true) {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToCharactersDetailsFragment(
                        it.id!!
                    )
                log(it.id.toString())
                findNavController().navigate(action)
                viewModel.onCompleteNavigation()
            } else {
                log("NOT Valid")
            }
        }
    }

    private fun initAdapter() {
        val adapter = SearchAdapter(viewModel)
        binding.rvSearchResult.adapter = adapter
        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }
}