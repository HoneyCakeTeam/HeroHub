package com.example.herohub.model

import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null,
)