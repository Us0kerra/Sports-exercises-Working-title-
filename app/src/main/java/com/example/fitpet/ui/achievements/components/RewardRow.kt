package com.example.fitpet.ui.achievements.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.achievements.Achievement
import com.example.fitpet.data.achievements.AchievementStatus

@Composable
fun RewardRow(achievement: Achievement, onClaimReward: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(achievement.rewardIcon),
            contentDescription = null,
            tint = Color(0xFF3B82F6),
            modifier = Modifier.size(24.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "Награда: ${achievement.rewardName}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        if (achievement.status == AchievementStatus.DONE_NOT_CLAIMED) {
            Spacer(Modifier.weight(1f))
            Button(onClick = onClaimReward) {
                Text("Забрать")
            }
        }
    }
}
