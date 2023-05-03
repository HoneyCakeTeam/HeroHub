package com.example.herohub.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.data.local.model.Category
import com.example.herohub.ui.base.BaseViewModel

class CategoryViewModel : BaseViewModel(), CategoryInteractionListener {
    override val TAG: String = this::class.java.simpleName

    private val repository = Repository()

    private val _itemCategory = MutableLiveData<List<Category>>()
    val itemCategory: LiveData<List<Category>> = _itemCategory

    init {
        getCategories()
    }

    private fun getCategories() {
        val categories = repository.getCategories()
        _itemCategory.postValue(categories)
    }


    override fun onCategoryClick(item: Category) {
    }

}