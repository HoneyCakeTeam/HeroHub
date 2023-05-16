package com.example.herohub.data.remote.model


import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("attributionHTML")
    val attributionHTML: String? = "",
    @SerializedName("attributionText")
    val attributionText: String? = "",
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("copyright")
    val copyright: String? = "",
    @SerializedName("data")
    val data: DataResponse<T>,
    @SerializedName("etag")
    val etag: String? = "",
    @SerializedName("status")
    val status: String? = "",
)