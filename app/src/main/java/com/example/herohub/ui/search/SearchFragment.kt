package com.example.herohub.ui.search


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSearchScreenBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.utils.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_search_screen
    override val viewModel: SearchViewModel by viewModels()
    private val adapter: SearchAdapter by lazy { SearchAdapter(viewModel) }

    override fun setup() {
        binding.rvSearchResult.adapter = adapter
        viewModel.eventClick.observe(this, EventObserve {
            val action =
                SearchFragmentDirections.actionSearchFragmentToCharactersDetailsFragment(
                    it.id!!
                )
            log(it.id.toString())
            findNavController().navigate(action)
        })
    }
}