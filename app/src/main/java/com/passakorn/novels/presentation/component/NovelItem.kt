package com.passakorn.novels.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.passakorn.novels.R
import com.passakorn.novels.presentation.isTablet
import com.passakorn.novels.presentation.model.NovelUiModel

@Composable
internal fun NovelItem(item: NovelUiModel?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .border(width = 1.dp, color = Color(0xFFF2F2F2))
            .clip(RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = item?.imageUrl ?: "",
                    contentDescription = item?.title,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .width(80.dp)
                        .height(120.dp)
                        .background(color = Color.Gray)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Crown(rank = item?.rank ?: 0)
                    Text(
                        item?.title ?: "",
                        style = TextStyle(
                            color = Color(0xFF333333),
                            fontSize = 18.sp,
                            fontWeight = FontWeight(700),
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        item?.category?.sub ?: "",
                        style = TextStyle(
                            color = Color(0xFFF37A01),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        item?.category?.main ?: "",
                        style = TextStyle(
                            color = Color(0xFF636363),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Row(
                modifier = Modifier.padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomBadge(R.drawable.ic_dot_list, item?.engagement?.views?.month ?: "")
                CustomBadge(R.drawable.ic_eye_solid, item?.engagement?.views?.overall ?: "")
                CustomBadge(R.drawable.ic_comment, item?.engagement?.comments?.overall ?: "")
                Spacer(modifier = Modifier.weight(1f))
                if (isTablet()) {
                    Text(
                        item?.updatedAt ?: "",
                        modifier = Modifier.padding(top = 8.dp),
                        style = TextStyle(
                            color = Color(0xFFA8A8A8),
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Text(
                item?.description ?: "",
                modifier = Modifier.padding(top = 8.dp),
                style = TextStyle(
                    color = Color(0xFF4C4C4C),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 22.sp,
                ),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )
            FlowRow(
                modifier = Modifier.padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item?.hashtags?.forEach { Hashtag(it) }
            }
            if (!isTablet()) {
                Text(
                    item?.updatedAt ?: "",
                    modifier = Modifier.padding(top = 8.dp),
                    style = TextStyle(
                        color = Color(0xFFA8A8A8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun Crown(rank: Int) {
    if (rank !in 1..3) return
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(42.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFF37A01),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Image(
            painter = when (rank) {
                1 -> painterResource(R.drawable.ic_crown_1)
                2 -> painterResource(R.drawable.ic_crown_2)
                else -> painterResource(R.drawable.ic_crown_3)
            },
            contentDescription = null,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            rank.toString(),
            style = TextStyle(
                color = Color(0xFFF37A01),
                fontSize = 12.sp,
                fontWeight = FontWeight(700)
            )
        )
    }
}

@Preview(widthDp = 358, heightDp = 329)
@Composable
private fun NovelItemPreview() {
    val exampleNovel = NovelUiModel(
        id = 1,
        title = "พญาหงส์ ภาคอัสดงที่ 1 (Rex  Pheonix The First Darkesadasd",
        imageUrl = "https://image.dek-d.com/27/0500/8631/129597405",
        rank = 1,
        category = NovelUiModel.Category(
            sub = "อดีต ปัจจุบัน อนาคต",
            main = "เสกขรเทวีพระนางหน่อเจ้าหลวง"
        ),
        description = "หลังจากเกิดสงครามหลังจากเกิดสงครามหลังจากเกิดสงคราม ผืนแผ่นดินได้ถูกทำลายไปมากมาก ทำให้ทำให้ทำ เกิดเรื่องแปลกประหลาดอยู่บ่อยครั้งทุกครั้งทุกทุกทุกทุก ครั้งที่เกิดเรื่องก็จะครั้งที่เกิดเรื่องก็จะก็จะ ครั้งที่เกิดเรื่อ iาวฟหกฟหก ฟหกฟหก ฟหก",
        hashtags = listOf("#ใหม่มาแรง", "#ใหม่มาแรง", "#ใหม่มาแรง", "#ใหม่มาแรง"),
        updatedAt = "อัปเดตล่าสุด 16 ก.ย. 64 / 20:37 น.",
        engagement = NovelUiModel.Engagement(
            views = NovelUiModel.Engagement.View(
                overall = "1000",
                month = "100.2k"
            ),
            comments = NovelUiModel.Engagement.Comment(
                overall = "100",
                primary = "10"
            )
        )
    )
    NovelItem(item = exampleNovel)
}

@Preview(widthDp = 650)
@Composable
private fun NovelItemTabletPreview() {
    val exampleNovel = NovelUiModel(
        id = 1,
        title = "พญาหงส์ ภาคอัสดงที่ 1 (Rex  Pheonix The First Darkesadasd",
        imageUrl = "https://image.dek-d.com/27/0500/8631/129597405",
        rank = 1,
        category = NovelUiModel.Category(
            sub = "อดีต ปัจจุบัน อนาคต",
            main = "เสกขรเทวีพระนางหน่อเจ้าหลวง"
        ),
        description = "หลังจากเกิดสงครามหลังจากเกิดสงครามหลังจากเกิดสงคราม ผืนแผ่นดินได้ถูกทำลายไปมากมาก ทำให้ทำให้ทำ เกิดเรื่องแปลกประหลาดอยู่บ่อยครั้งทุกครั้งทุกทุกทุกทุก ครั้งที่เกิดเรื่องก็จะครั้งที่เกิดเรื่องก็จะก็จะ ครั้งที่เกิดเรื่อ iาวฟหกฟหก ฟหกฟหก ฟหก",
        hashtags = listOf("#ใหม่มาแรง", "#ใหม่มาแรง", "#ใหม่มาแรง", "#ใหม่มาแรง"),
        updatedAt = "อัปเดตล่าสุด 16 ก.ย. 64 / 20:37 น.",
        engagement = NovelUiModel.Engagement(
            views = NovelUiModel.Engagement.View(
                overall = "1000",
                month = "100.2k"
            ),
            comments = NovelUiModel.Engagement.Comment(
                overall = "100",
                primary = "10"
            )
        )
    )
    NovelItem(item = exampleNovel)
}