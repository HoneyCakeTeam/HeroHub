package com.example.herohub.ui.characterdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.herohub.BR
import com.example.herohub.R
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener
import com.example.herohub.ui.characterdetails.CharacterDetailsItem

/**
 * Created by Aziza Helmy on 5/11/2023.
 */
class CharacterDetailsAdapter(
    private var characterDetailsItems: MutableList<CharacterDetailsItem>,
    val listener: BaseInteractionListener,
) : BaseAdapter<CharacterDetailsItem>(listener) {

    override val layoutId: Int = 0

    override fun setItems(newItems: List<CharacterDetailsItem>) {
        characterDetailsItems = newItems.sortedBy { it.position }.toMutableList()
        super.setItems(characterDetailsItems)
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
                is CharacterDetailsItem.CharacterInfo -> {
                    holder.binding.setVariable(
                        BR.item,
                        currentItem.character
                    )
                }

                is CharacterDetailsItem.CharacterComics -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        ComicsAdapter(listener as ComicsInteractionListener).apply {
                            setItems(currentItem.comics)
                        }


                    )
                }

                is CharacterDetailsItem.CharacterEvents -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        EventsAdapter(listener as EventsInteractionListener).apply {
                            setItems(currentItem.events)
                        }

                    )
                }

                is CharacterDetailsItem.CharacterSeries -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        SeriesAdapter(listener as SeriesInteractionListener).apply {
                            setItems(currentItem.series)
                        }

                    )
                }
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