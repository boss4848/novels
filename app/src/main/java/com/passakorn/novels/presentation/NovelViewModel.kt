package com.passakorn.novels.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.passakorn.novels.data.model.NovelItem
import com.passakorn.novels.domain.mapper.CampaignListResponseMapper
import com.passakorn.novels.domain.mapper.NovelListResponseMapper
import com.passakorn.novels.domain.usecase.GetCampaignListUseCase
import com.passakorn.novels.domain.usecase.GetNovelListUseCase
import com.passakorn.novels.presentation.model.NovelItemUiModel
import com.passakorn.novels.presentation.model.SectionUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.mapNotNull

class NovelViewModel(
    private val getNovelListUseCase: GetNovelListUseCase,
    private val getCampaignListUseCase: GetCampaignListUseCase,
    private val novelListResponseMapper: NovelListResponseMapper,
    private val campaignListResponseMapper: CampaignListResponseMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NovelUiState.initial())
    val uiState: StateFlow<NovelUiState> = _uiState

    private var currentPage = 1

    init {
        getNovels()
    }

    private fun getNovels() {
        viewModelScope.launch {
            _uiState.update { it.copy(state = NovelUiState.State.LOADING) }

            when (val result = getNovelListUseCase.invoke(GetNovelListUseCase.Params(page = 1))) {
                is GetNovelListUseCase.Result.Success -> {
                    val novelItems = updateNovelUiModels(result.novels.list)
                    _uiState.update {
                        it.copy(
                            state = NovelUiState.State.SUCCESS,
                            novels = novelItems,
                            pageInfo = result.novels.pageInfo,
                            isLoadingMore = false
                        )
                    }
                }

                is GetNovelListUseCase.Result.Failure -> {
                    _uiState.update {
                        it.copy(state = NovelUiState.State.ERROR, novels = emptyList())
                    }
                }
            }
        }
    }

    fun loadNextPage() {
        val state = uiState.value
        if (state.isLoadingMore || state.pageInfo?.hasNext != true) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMore = true) }

            val nextPage = currentPage + 1
            when (val result = getNovelListUseCase.invoke(GetNovelListUseCase.Params(page = nextPage))) {
                is GetNovelListUseCase.Result.Success -> {
                    val newItems = updateNovelUiModels(result.novels.list)
                    currentPage = nextPage
                    _uiState.update {
                        it.copy(
                            novels = it.novels + newItems,
                            pageInfo = result.novels.pageInfo,
                            isLoadingMore = false
                        )
                    }
                }

                is GetNovelListUseCase.Result.Failure -> {
                    _uiState.update { it.copy(isLoadingMore = false) }
                }
            }
        }
    }

    private suspend fun updateNovelUiModels(list: List<NovelItem>): List<NovelItemUiModel> {
        return list.mapNotNull { item ->
            when {
                item.section != null -> {
                    when (val campaignResult = getCampaignListUseCase()) {
                        is GetCampaignListUseCase.Result.Success -> {
                            val section = item.section
                            val campaigns = campaignListResponseMapper.mapToCampaignItemUiModel(campaignResult.campaigns)
                            val sectionUiModel = SectionUiModel(
                                type = section.type,
                                title = section.title,
                                path = section.path,
                                campaigns = campaigns
                            )
                            NovelItemUiModel.Section(sectionUiModel)
                        }

                        // Skip if campaign fetch failed
                        else -> null
                    }
                }

                item.novel != null -> {
                    val uiModel = novelListResponseMapper.mapToNovelUiModel(item.novel)
                    NovelItemUiModel.Novel(uiModel)
                }

                else -> null
            }
        }
    }
}