package com.passakorn.novels.presentation.model

sealed class NovelItemUiModel {
    data class Novel(val data: NovelUiModel?) : NovelItemUiModel()
    data class Section(val data: SectionUiModel) : NovelItemUiModel()
}