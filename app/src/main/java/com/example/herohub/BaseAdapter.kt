package com.example.herohub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Aziza Helmy on 5/2/2023.
 */
abstract class BaseAdapter<T>(private val listener: BaseInteractionListener) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    private var items = arrayListOf<T>()
    abstract val layoutId: Int

    fun setItems(newItems: List<T>) {
        items = newItems as ArrayList<T>
        val diffResult =
            DiffUtil.calculateDiff(BaseDiffUtil(items, newItems, ::areItemsSame, ::areContentSame))
        diffResult.dispatchUpdatesTo(this)
    }

    open fun areItemsSame(oldItem: T, newItem: T) = oldItem?.equals(newItem) == true
    open fun areContentSame(oldPosition: T, newPosition: T) = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.apply {
//                    setVariable(BR.item,currentItem)
//                    setVariable(BR.listner,listener)

                }
            }
        }
    }

    override fun getItemCount(): Int = items.size


    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}

interface BaseInteractionListener

