package com.passakorn.novels.presentation.model

data class CampaignItemUiModel(
    val url: String,
    val imageUrl: ImageUrl,
) {
    data class ImageUrl(
        val mobile: String,
        val tablet: String,
    )
}