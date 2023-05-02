package com.example.herohub.utills

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:showWhenLoading")
fun <T> showWhenLoading(view: View, state: State<T>?){
    if (state is State.Loading) view.visibility=View.VISIBLE
    else view.visibility=View.VISIBLE
}
@BindingAdapter("app:showWhenError")
fun <T> showWhenError(view: View, state: State<T>?){
    if (state is State.Error) view.visibility=View.VISIBLE
    else view.visibility=View.VISIBLE
}
@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, state: State<T>?){
    if (state is State.Success) view.visibility=View.VISIBLE
    else view.visibility=View.VISIBLE
}