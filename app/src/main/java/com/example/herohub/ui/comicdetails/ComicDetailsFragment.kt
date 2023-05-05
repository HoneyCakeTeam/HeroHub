package com.example.herohub.ui.comicdetails

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentComicsDetailsBinding
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.utills.UiState

class ComicDetailsFragment : BaseFragment<FragmentComicsDetailsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_comics_details
    override val viewModel: ComicDetailsViewModel by viewModels()

    override fun setup() {
        viewModel.getComic(333)
    }


}