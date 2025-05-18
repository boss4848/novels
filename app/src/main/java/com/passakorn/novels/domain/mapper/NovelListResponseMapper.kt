package com.passakorn.novels.domain.mapper

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.passakorn.novels.data.model.Novel
import com.passakorn.novels.data.model.NovelListResponse
import com.passakorn.novels.data.model.Section
import com.passakorn.novels.presentation.model.NovelItemUiModel
import com.passakorn.novels.presentation.model.NovelUiModel
import com.passakorn.novels.presentation.model.SectionUiModel
import java.text.DecimalFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class NovelListResponseMapper {

    internal fun mapToNovelItemUiModel(response: NovelListResponse): List<NovelItemUiModel> {
        return response.list.mapNotNull { item ->
            when {
                item.section != null -> {
                    mapToSectionUiModel(item.section)?.let {
                        NovelItemUiModel.Section(it)
                    }
                }

                item.novel != null -> {
                    mapToNovelUiModel(item.novel)?.let {
                        NovelItemUiModel.Novel(it)
                    }
                }

                else -> null
            }
        }
    }

    internal fun mapToSectionUiModel(section: Section): SectionUiModel? {
        return section.let {
            SectionUiModel(
                type = it.type,
                title = it.title,
                path = it.path,
            )
        }
    }

    internal fun mapToNovelUiModel(novel: Novel?): NovelUiModel? {
        return novel?.let {
            NovelUiModel(
                id = it.id,
                title = it.title,
                imageUrl = it.thumbnail.normal,
                rank = it.order,
                category = NovelUiModel.Category(
                    main = it.category.mainTitle,
                    sub = it.category.subTitle,
                ),
                description = it.description,
                engagement = NovelUiModel.Engagement(
                    views = NovelUiModel.Engagement.View(
                        overall = transformViewCount(it.engagement.view.overall),
                        month = transformViewCount(it.engagement.view.month)
                    ),
                    comments = NovelUiModel.Engagement.Comment(
                        overall = transformViewCount(it.engagement.comment.overall),
                        primary = transformViewCount(it.engagement.comment.primary),
                    )
                ),
                hashtags = it.tags,
                updatedAt = transformUpdatedAt(it.updatedAt),
            )
        }
    }

    @SuppressLint("NewApi")
    private fun transformUpdatedAt(updatedAt: String): String {
        val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val outputDateFormatter = DateTimeFormatter.ofPattern("d MMM yy", Locale("th", "TH"))
        val outputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale("th", "TH"))

        return try {
            val dateTime = ZonedDateTime.parse(updatedAt, inputFormatter)
            val formattedDate = dateTime.format(outputDateFormatter)
            val formattedTime = dateTime.format(outputTimeFormatter)
            "อัปเดตล่าสุด $formattedDate / $formattedTime น."
        } catch (e: Exception) {
            "อัปเดตล่าสุดไม่พบเวลา"
        }
    }

    private fun transformViewCount(count: Int): String {
        return when {
            count >= 1_000_000 -> {
                val value = count / 1_000_000.0
                DecimalFormat("#.#").format(value) + "M"
            }

            count >= 1_000 -> {
                val value = count / 1_000.0
                DecimalFormat("#.#").format(value) + "K"
            }

            else -> count.toString()
        }
    }
}