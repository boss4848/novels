package com.passakorn.novels.data.model

import com.google.gson.annotations.SerializedName

data class CampaignListResponse(
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("list") val list: List<CampaignItem>
)

data class CampaignItem(
    @SerializedName("id") val id: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("startedAt") val startedAt: String,
    @SerializedName("endedAt") val endedAt: String,
    @SerializedName("published") val published: Boolean,
    @SerializedName("payload") val payload: Payload
)

data class Payload(
    @SerializedName("url") val url: String,
    @SerializedName("imageUrl") val imageUrl: ImageUrl
)

data class ImageUrl(
    @SerializedName("mobile") val mobile: String,
    @SerializedName("tablet") val tablet: String
)