package com.example.herohub.model


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









