package com.example.herohub.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.herohub.R
import com.example.herohub.BR
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
                is HomeItem.Slider -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        SliderAdapter(listener as SliderInteractionListener)
                    )
                    holder.binding.setVariable(BR.item, currentItem.slider)
                }

                is HomeItem.SuperHeroes -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        SuperHeroesAdapter(listener as SuperHeroesInteractionListener)
                    )
                    holder.binding.setVariable(BR.item, currentItem.superHeroes)

                }

                is HomeItem.MostPopularComics -> {
                    holder.binding.setVariable(
                        BR.adapterRecycler,
                        MostPopularComicsAdapter(listener as MostPopularComicsInteractionListener)
                    )
                    holder.binding.setVariable(BR.item, currentItem.mostPopularComics)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (homeItems.isNotEmpty()) {
            return when (homeItems[position]) {
                is HomeItem.Slider -> R.layout.item_slider
                is HomeItem.SuperHeroes -> R.layout.item_super_heroes
                is HomeItem.MostPopularComics -> R.layout.item_most_popular_comics
            }
        }
        return -1
    }

    override fun areItemsSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.position == newItem.position
    }

    override fun areContentSame(
        oldPosition: HomeItem,
        newPosition: HomeItem,
    ): Boolean {
        return oldPosition == newPosition
    }
}