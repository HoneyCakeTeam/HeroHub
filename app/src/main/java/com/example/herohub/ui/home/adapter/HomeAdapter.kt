package com.example.herohub.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.herohub.BR
import com.example.herohub.R
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener
import com.example.herohub.ui.home.HomeItem

class HomeAdapter(
    private var homeItems: MutableList<HomeItem>,
    val listener: BaseInteractionListener,
) : BaseAdapter<HomeItem>(listener) {

    override val layoutId: Int = 0

    override fun setItems(newItems: List<HomeItem>) {
        homeItems = newItems.sortedBy { it.position }.toMutableList()

        super.setItems(homeItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (homeItems.isNotEmpty())
            bind(holder as ItemViewHolder, position)
    }

    private fun bind(holder: ItemViewHolder, position: Int) {
        if (position != -1) {
            when (val currentItem = homeItems[position]) {
                is HomeItem.CharactersByAppearance -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        CharactersByAppearanceAdapter(listener as CharactersByAppearanceInteractionListener)
                            .apply { setItems(currentItem.charactersByAppearance) }
                    )
                }

                is HomeItem.SuperHeroes -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        SuperHeroesAdapter(listener as SuperHeroesInteractionListener)
                            .apply { setItems(currentItem.superHeroes) }
                    )
                }

                is HomeItem.MostPopularSeries -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        MostPopularSeriesAdapter(listener as MostPopularSeriesInteractionListener)
                            .apply { setItems(currentItem.mostPopularSeries) }
                    )
                }

                is HomeItem.Slider -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        SliderAdapter(listener as SliderInteractionListener)
                            .apply { setItems(currentItem.slider) }
                    )
                }

                is HomeItem.MostPopularEvents -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        MostPopularEventsAdapter(listener as MostPopularEventsInteractionListener)
                            .apply { setItems(currentItem.mostPopularEvents) }
                    )
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (homeItems.isNotEmpty()) {
            return when (homeItems[position]) {
                is HomeItem.CharactersByAppearance -> R.layout.layout_characters_by_appearanace
                is HomeItem.SuperHeroes -> R.layout.layout_super_heroes
                is HomeItem.MostPopularSeries -> R.layout.layout_most_popular_series
                is HomeItem.Slider -> R.layout.layout_slider
                is HomeItem.MostPopularEvents -> R.layout.layout_most_popular_events
            }
        }
        return -1
    }

    override fun areItemsSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.position == newItem.position
    }

    override fun areContentSame(oldPosition: HomeItem, newPosition: HomeItem): Boolean {
        return oldPosition == newPosition
    }
}