package com.passakorn.novels.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.passakorn.novels.R

@Composable
internal fun CustomBadge(@DrawableRes imageRes: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text, style = TextStyle(
                color = Color(0xFFA8A8A8),
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
            )
        )
    }
}

@Preview
@Composable
private fun CustomBadgePreview() {
    CustomBadge(
        imageRes = R.drawable.ic_comment,
        text = "111.2K"
    )
}