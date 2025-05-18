package com.passakorn.novels.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun Hashtag(text: String) {
    Text(
        text,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFF2F2F2),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(6.dp),
        style = TextStyle(
            color = Color(0xFF636363),
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
        )
    )
}

@Preview
@Composable
private fun HashtagPreview() {
    Hashtag("#ใหม่มาแรง")
}
