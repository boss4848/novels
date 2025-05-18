package com.passakorn.novels.presentation.model

data class SectionUiModel(
    val type: String,
    val title: String? = null,
    val path: String,
    val campaigns: List<CampaignItemUiModel>? = null,
)