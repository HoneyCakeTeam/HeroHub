package com.example.herohub.utills

import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.herohub.R
import com.example.herohub.ui.base.BaseAdapter

@BindingAdapter("app:showWhenLoading")
fun <T> showWhenLoading(view: View, UiState: UiState<T>?) {
    if (UiState is UiState.Loading) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter("app:showWhenError")
fun <T> showWhenError(view: View, UiState: UiState<T>?) {
    if (UiState is UiState.Error) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, UiState: UiState<T>?) {
    if (UiState is UiState.Success) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:showIfEmpty"])
fun <T> setPlaceHolder(view: View, list: List<T>) {
    if (list.isEmpty()) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.INVISIBLE
    }
}

//@BindingAdapter("app:query")
//fun setQuery(view: SearchView, query: MutableLiveData<String>) {
//    view.setQuery(query.value, false)
//    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//        override fun onQueryTextSubmit(queryText: String?): Boolean {
//            query.value = queryText ?: ""
//            return false
//        }
//
//        override fun onQueryTextChange(newText: String?): Boolean {
//            query.value = newText ?: ""
//            return true
//        }
//    })
//}
//
//@InverseBindingAdapter(attribute = "app:query")
//fun getQuery(view: SearchView): String {
//    return view.query.toString()
//}


@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl(view: ImageView, url: String?) {
    Glide
        .with(view)
        .load("$url.jpg")
        .placeholder(R.drawable.logo)
        .error(R.drawable.error)
        .centerCrop().into(view)
}