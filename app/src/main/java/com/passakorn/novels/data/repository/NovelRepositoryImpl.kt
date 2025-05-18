package com.passakorn.novels.data.repository

import com.passakorn.novels.data.datasource.remote.NovelRemoteDataSource
import com.passakorn.novels.data.model.CampaignListResponse
import com.passakorn.novels.data.model.NovelListResponse

class NovelRepositoryImpl(
    private val novelRemoteDataSource: NovelRemoteDataSource,
) : NovelRepository {
    override suspend fun getNovelList(page: Int?): NovelListResponse {
        return novelRemoteDataSource.getNovelList(
            page = page,
        )
    }

    override suspend fun getCampaignList(): CampaignListResponse {
        return novelRemoteDataSource.getCampaignList()
    }
}