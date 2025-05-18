package com.passakorn.novels.presentation

import com.passakorn.novels.data.model.PageInfo
import com.passakorn.novels.presentation.model.NovelItemUiModel

data class NovelUiState(
    val state: State,
    val novels: List<NovelItemUiModel>,
    val pageInfo: PageInfo? = null,
    val isLoadingMore: Boolean = false
) {
    enum class State { LOADING, ERROR, SUCCESS }

    companion object {
        fun initial() = NovelUiState(State.LOADING, emptyList())
    }
}