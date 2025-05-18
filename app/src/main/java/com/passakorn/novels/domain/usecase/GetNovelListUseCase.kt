package com.passakorn.novels.domain.usecase

import com.passakorn.novels.data.model.NovelListResponse
import com.passakorn.novels.data.repository.NovelRepository

class GetNovelListUseCase(
    private val repository: NovelRepository
) {
    data class Params(
        val page: Int = 1,
    )

    sealed class Result {
        data class Success(val novels: NovelListResponse) : Result()
        data class Failure(val exception: Exception) : Result()
    }

    suspend operator fun invoke(params: Params): Result {
        return try {
            val response = repository.getNovelList(params.page)
            Result.Success(novels = response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(e)
        }
    }
}