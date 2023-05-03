package com.example.herohub.ui.search

import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseDiffUtil

class DiffUtils(private val OldList: List<Character>, private val NewList: List<Character>,
                checkIfSameItem: (Character, Character) -> Boolean,
                checkIfSameContent: (Character, Character) -> Boolean) :
    BaseDiffUtil<Character>(OldList, NewList, checkIfSameItem, checkIfSameContent){
}
