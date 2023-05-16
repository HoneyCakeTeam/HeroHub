package com.example.herohub.ui.character

import com.example.herohub.R
import com.example.herohub.data.remote.model.Character
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class CharactersAdapter(listener: CharactersInteractionListener) :
    BaseAdapter<Character>(listener) {
    override val layoutId: Int = R.layout.item_character
}

interface CharactersInteractionListener : BaseInteractionListener{
    fun onClickCharacter(id: Int)
}