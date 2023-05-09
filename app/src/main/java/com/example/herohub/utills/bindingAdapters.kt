package com.example.herohub.utills

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
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

@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl(view: ImageView, url: String?) {
    url?.let {
        if (url.contains("image_not_available")) {
            Glide
                .with(view)
                .load(R.drawable.no_image_placeholder)
                .placeholder(R.drawable.slider_placeholder)
                .error(R.drawable.no_image_placeholder)
                .centerCrop().into(view)
        } else {
            Glide
                .with(view)
                .load("$url.jpg")
                .placeholder(R.drawable.slider_placeholder)
                .error(R.drawable.no_image_placeholder)
                .centerCrop().into(view)
        }
    }

}