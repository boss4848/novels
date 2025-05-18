package com.passakorn.novels.presentation.model

data class NovelUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rank: Int,
    val category: Category,
    val description: String,
    val hashtags: List<String>,
    val updatedAt: String,
    val engagement: Engagement,
) {
    data class Category(
        val main: String,
        val sub: String,
    )

    data class Engagement(
        val views: View,
        val comments: Comment,
    ) {
        data class View(
            val overall: String,
            val month: String,
        )

        data class Comment(
            val overall: String,
            val primary: String,
        )
    }
}
