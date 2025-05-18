package com.passakorn.novels.data.repository

import com.passakorn.novels.data.model.CampaignListResponse
import com.passakorn.novels.data.model.NovelListResponse

interface NovelRepository {
    suspend fun getNovelList(page: Int? = 1): NovelListResponse
    suspend fun getCampaignList(): CampaignListResponse
}