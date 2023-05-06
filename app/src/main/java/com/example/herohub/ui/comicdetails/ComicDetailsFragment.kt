package com.example.herohub.ui.comicdetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.herohub.R
import com.example.herohub.databinding.FragmentComicsDetailsBinding
import com.example.herohub.ui.base.BaseFragment

class ComicDetailsFragment : BaseFragment<FragmentComicsDetailsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_comics_details
    override val viewModel: ComicDetailsViewModel by viewModels()
    private val args : ComicDetailsFragmentArgs by navArgs()
    override fun setup() {
        viewModel.getComic(args.comicId)
    }

}