package com.passakorn.novels.data.model

import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("numberOfPages") val numberOfPages: Int,
    @SerializedName("itemsPerPage") val itemsPerPage: Int,
    @SerializedName("hasPrevious") val hasPrevious: Boolean,
    @SerializedName("hasNext") val hasNext: Boolean
)