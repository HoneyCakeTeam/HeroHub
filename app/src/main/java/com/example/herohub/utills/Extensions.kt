package com.example.herohub.utills

import android.view.View
import androidx.navigation.Navigation

fun Navigation.navigateTo(view: View, destinationId: Int) {
    Navigation.findNavController(view).navigate(destinationId)
}