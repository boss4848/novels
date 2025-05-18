package com.passakorn.novels.data.datasource.remote

import com.passakorn.novels.BuildConfig
import com.passakorn.novels.data.model.CampaignListResponse
import com.passakorn.novels.data.model.NovelListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NovelRemoteDataSource {
    @GET("/api/rest/open/quiz/novel/list")
    suspend fun getNovelList(
        @Query("page") page: Int? = 1,
        @Header("SECRET") secret: String? = BuildConfig.NOVEL_SECRET
    ): NovelListResponse

    @GET("/api/rest/campaign/list")
    suspend fun getCampaignList(): CampaignListResponse
}