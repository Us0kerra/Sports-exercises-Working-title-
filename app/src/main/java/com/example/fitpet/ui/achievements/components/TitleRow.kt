package com.example.fitpet.ui.achievements.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.achievements.Achievement

@Composable
fun TitleRow(achievement: Achievement) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = achievement.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        if (achievement.status != com.example.fitpet.data.achievements.AchievementStatus.NOT_DONE) {
            Spacer(Modifier.width(8.dp))
            StatusBadge(achievement.status)
        }
    }
}
