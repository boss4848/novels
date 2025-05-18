package com.passakorn.novels.data.model

import com.google.gson.annotations.SerializedName

data class NovelListResponse(
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("list") val list: List<NovelItem>
)

data class NovelItem(
    @SerializedName("novel") val novel: Novel? = null,
    @SerializedName("section") val section: Section? = null,
)

data class Novel(
    @SerializedName("id") val id: Int,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("endCreatedAt") val endCreatedAt: String? = null,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String,
    @SerializedName("description") val description: String,
    @SerializedName("totalChapter") val totalChapter: Int,
    @SerializedName("category") val category: Category,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("owners") val owners: List<Owner>,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("engagement") val engagement: Engagement,
    @SerializedName("order") val order: Int
)

data class Category(
    @SerializedName("main") val main: Int,
    @SerializedName("sub") val sub: Int,
    @SerializedName("mainTitle") val mainTitle: String,
    @SerializedName("subTitle") val subTitle: String,
    @SerializedName("mainTitleEn") val mainTitleEn: String,
    @SerializedName("subTitleEn") val subTitleEn: String,
    @SerializedName("groupTitle") val groupTitle: String,
    @SerializedName("groupTitleEn") val groupTitleEn: String,
)

data class Owner(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("role") val role: String? = null,
)

data class Thumbnail(
    @SerializedName("normal") val normal: String,
    @SerializedName("landscape") val landscape: String
)

data class Engagement(
    @SerializedName("view") val view: View,
    @SerializedName("comment") val comment: Comment
)

data class View(
    @SerializedName("month") val month: Int,
    @SerializedName("overall") val overall: Int
)

data class Comment(
    @SerializedName("primary") val primary: Int,
    @SerializedName("overall") val overall: Int
)

data class Section(
    @SerializedName("type") val type: String,
    @SerializedName("title") val title: String?,
    @SerializedName("path") val path: String
)