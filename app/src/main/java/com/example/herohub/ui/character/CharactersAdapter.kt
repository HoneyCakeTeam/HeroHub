package com.example.herohub.ui.character

import com.example.herohub.R
import com.example.herohub.ui.category.CategoryInteractionListener
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseAdapter

class CharactersAdapter(listener: CharactersInteractionListener) :
    BaseAdapter<Character>(listener) {
    override val layoutId: Int = R.layout.item_category
}

interface CharactersInteractionListener : CategoryInteractionListener