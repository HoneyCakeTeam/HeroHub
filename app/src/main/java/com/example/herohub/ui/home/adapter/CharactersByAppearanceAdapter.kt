package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class CharactersByAppearanceAdapter(listener: CharactersByAppearanceInteractionListener) :
    BaseAdapter<Character>(listener) {

    override val layoutId: Int = R.layout.item_slider
}

interface CharactersByAppearanceInteractionListener : BaseInteractionListener {

    fun onSeriesSliderItemClick(id: Int)
}