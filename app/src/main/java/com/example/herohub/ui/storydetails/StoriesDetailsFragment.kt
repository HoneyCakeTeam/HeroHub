package com.example.herohub.ui.storydetails

import androidx.lifecycle.ViewModel
import com.example.herohub.R
import com.example.herohub.data.Repository
import com.example.herohub.databinding.FragmentStoriesDetailsBinding
import com.example.herohub.ui.base.BaseFragment

class StoriesDetailsFragment  :BaseFragment<FragmentStoriesDetailsBinding>() {
    override val TAG: String = this::class.simpleName.toString()
    override val layoutIdFragment: Int
        get() = R.id.stories_details_fragment
    override val viewModel: ViewModel
        get() = StoryDetailsViewModel(repository = Repository())

    override fun setup() {
        TODO("Not yet implemented")
    }
    private fun getStories(id:String){


    }


}