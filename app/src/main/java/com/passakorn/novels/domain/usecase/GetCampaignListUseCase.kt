package com.passakorn.novels.domain.usecase

import com.passakorn.novels.data.model.CampaignListResponse
import com.passakorn.novels.data.repository.NovelRepository

class GetCampaignListUseCase(
    private val repository: NovelRepository,
) {

    sealed class Result {
        data class Success(val campaigns: CampaignListResponse) : Result()
        data class Failure(val exception: Exception) : Result()
    }

    suspend operator fun invoke(): Result {
        return try {
            val response = repository.getCampaignList()
            Result.Success(campaigns = response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(e)
        }
    }
}