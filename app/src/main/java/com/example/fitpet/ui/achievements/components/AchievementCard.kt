package com.example.fitpet.ui.achievements.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.achievements.Achievement
import com.example.fitpet.data.achievements.AchievementStatus


@Composable
fun AchievementCard(
    achievement: Achievement,
    onClaimReward: () -> Unit
) {
    val cardColor = when(achievement.status) {
        AchievementStatus.NOT_DONE -> Color(0xFFF7F0F6)
        AchievementStatus.DONE_NOT_CLAIMED -> Color(0xFFFFF7E6)
        AchievementStatus.DONE_CLAIMED -> Color(0xFFEEF9F6)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AchievementIcon(achievement.typeIcon, achievement.status)

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    TitleRow(achievement)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = achievement.description,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            if (achievement.status == AchievementStatus.NOT_DONE) {
                LinearProgressIndicator(
                    progress = { achievement.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )
            } else {
                RewardRow(achievement = achievement, onClaimReward = onClaimReward)
            }
        }
    }
}
