package com.example.herohub.ui.characterdetails.adapter

import android.view.ViewGroup
import com.example.herohub.R
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener
import com.example.herohub.ui.characterdetails.CharacterDetailsItem

/**
 * Created by Aziza Helmy on 5/11/2023.
 */
class CharacterDetailsAdapter(
    private var characterDetailsItems: MutableList<CharacterDetailsItem>,
    listener: BaseInteractionListener
) :
    BaseAdapter<CharacterDetailsItem>(listener) {

    override val layoutId: Int = 0

    override fun setItems(newItems: List<CharacterDetailsItem>) {
        characterDetailsItems = newItems.sortedBy { it.position }.toMutableList()
        super.setItems(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        if (characterDetailsItems.isNotEmpty()) {
            return when (characterDetailsItems[position]) {
                is CharacterDetailsItem.CharacterInfo -> R.layout.layout_character_info
                is CharacterDetailsItem.CharacterEvents -> R.layout.layout_character_events
                is CharacterDetailsItem.CharacterComics -> R.layout.layout_character_comics
                is CharacterDetailsItem.CharacterSeries -> R.layout.layout_character_series
            }
        }
        return -1
    }
}