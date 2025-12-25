package com.example.fitpet.ui.achievements.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.fitpet.data.achievements.AchievementStatus
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun StatusBadge(status: AchievementStatus) {
    if (
        status != AchievementStatus.DONE_NOT_CLAIMED
    ) return

    val backgroundColor = when (status) {
        AchievementStatus.DONE_NOT_CLAIMED -> Color(0xFFFFF7E6)
        else -> Color.Transparent
    }

    val textColor = when (status) {
        AchievementStatus.DONE_NOT_CLAIMED -> Color(0xFFB45309)
        else -> Color.Unspecified
    }

    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "ГОТОВО!",
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
