package com.example.herohub.ui.category.characters

import com.example.herohub.R
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class CharactersAdapter(listener: CharactersInteractionListener) :
    BaseAdapter<Character>(listener) {
    override val layoutId: Int = R.layout.item_category
}

interface CharactersInteractionListener : BaseInteractionListener {
    fun onClickCharacter(id: Int)
}