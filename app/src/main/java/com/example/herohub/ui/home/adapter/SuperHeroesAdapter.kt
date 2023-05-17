package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.domin.model.Character
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class SuperHeroesAdapter(listener: SuperHeroesInteractionListener) :
    BaseAdapter<Character>(listener) {

    override val layoutId: Int = R.layout.item_super_heroes
}

interface SuperHeroesInteractionListener : BaseInteractionListener {

    fun onSuperHeroesItemClick(id: Int)
    fun onSeeAllCharactersClicked()
}