package com.example.herohub.data.remote.model

import com.google.gson.annotations.SerializedName

data class DataResponse<T>(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("offset")
    val offset: Int? = 0,
    @SerializedName("results")
    val results: List<T>? = emptyList(),
    @SerializedName("total")
    val total: Int? = 0,
)