package com.example.herohub.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.local.model.Category
import com.example.herohub.ui.base.BaseViewModel

class CategoryViewModel:BaseViewModel() , CategoryInteractionListener{
    override val TAG: String = this::class.java.simpleName

    private val _itemCategory = MutableLiveData<Category>()
    val itemCategory:LiveData<Category> = _itemCategory
    override fun onCategoryClick(item: Category) {
        _itemCategory.postValue(item)
    }

}