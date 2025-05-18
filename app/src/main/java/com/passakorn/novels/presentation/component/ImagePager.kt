package com.passakorn.novels.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.passakorn.novels.presentation.isTablet
import com.passakorn.novels.presentation.model.CampaignItemUiModel

@Composable
internal fun ImagePager(imageUrls: List<CampaignItemUiModel>?) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { imageUrls?.size ?: 0 }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) { page ->
        val imageUrl = if (isTablet()) {
            imageUrls?.get(page)?.imageUrl?.tablet
        } else {
            imageUrls?.get(page)?.imageUrl?.mobile
        }
        AsyncImage(
            model = imageUrl,
            contentDescription = "Image $page",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentScale = ContentScale.Crop
        )
    }
}
