package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class MostPopularCharactersAdapter(listener: MostPopularCharactersInteractionListener) :
    BaseAdapter<Character>(listener) {

    override val layoutId: Int = R.layout.item_most_popular_comics
}

interface MostPopularCharactersInteractionListener : BaseInteractionListener {

    fun onMostPopularCharactersItemClick(id: Int)
}