package com.example.herohub.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.example.herohub.model.Character

class DiffUtils(private val OldList: List<Character>, private val NewList: List<Character>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = OldList.size
    override fun getNewListSize() = NewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (OldList[oldItemPosition].id == NewList[newItemPosition].id
                || OldList[oldItemPosition].name == NewList[newItemPosition].name)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}
