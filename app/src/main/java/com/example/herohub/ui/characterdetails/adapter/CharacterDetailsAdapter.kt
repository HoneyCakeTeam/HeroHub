package com.example.herohub.ui.characterdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.herohub.BR
import com.example.herohub.R
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener
import com.example.herohub.ui.characterdetails.CharacterDetailsItem
import com.example.herohub.ui.home.HomeItem
import com.example.herohub.ui.home.adapter.MostPopularComicsAdapter
import com.example.herohub.ui.home.adapter.MostPopularComicsInteractionListener
import com.example.herohub.ui.home.adapter.MostPopularEventsAdapter
import com.example.herohub.ui.home.adapter.MostPopularEventsInteractionListener
import com.example.herohub.ui.home.adapter.MostPopularSeriesAdapter
import com.example.herohub.ui.home.adapter.MostPopularSeriesInteractionListener
import com.example.herohub.ui.home.adapter.SliderAdapter
import com.example.herohub.ui.home.adapter.SliderInteractionListener
import com.example.herohub.ui.home.adapter.SuperHeroesAdapter
import com.example.herohub.ui.home.adapter.SuperHeroesInteractionListener

/**
 * Created by Aziza Helmy on 5/11/2023.
 */
class CharacterDetailsAdapter(
    private var characterDetailsItems: MutableList<CharacterDetailsItem>,
    listener: BaseInteractionListener
) : BaseAdapter<CharacterDetailsItem>(listener) {

    override val layoutId: Int = 0

    override fun setItems(newItems: List<CharacterDetailsItem>) {
        characterDetailsItems = newItems.sortedBy { it.position }.toMutableList()
        super.setItems(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (characterDetailsItems.isNotEmpty())
            bind(holder as ItemViewHolder, position)
    }
    private fun bind(holder: ItemViewHolder, position: Int) {
        if (position != -1) {
            when (val currentItem = characterDetailsItems[position]) {
                is CharacterDetailsItem.CharacterComics -> {

                }
                is CharacterDetailsItem.CharacterEvents -> TODO()
                is CharacterDetailsItem.CharacterInfo -> TODO()
                is CharacterDetailsItem.CharacterSeries -> TODO()
            }
        }
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