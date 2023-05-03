package com.example.herohub.ui.storydetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.herohub.R
import com.example.herohub.data.Repository
import com.example.herohub.databinding.FragmentStoriesDetailsBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.storydetails.viewmodel.StoryDetailsViewModel

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