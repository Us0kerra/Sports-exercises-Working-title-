package com.example.fitpet.ui.achievements
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpet.ui.achievements.components.AchievementCard
import com.example.fitpet.ui.settings.components.SettingSwitch

@Composable
fun AchievementsScreen(viewModel: AchievementsViewModel) {
    val achievements by viewModel.achievements.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(
            text = "Ваши достижения",
            fontSize = 18.sp, // 1.125rem
            color = Color(0xFF9810FA)
        )

        Text(
            text = "Открывайте одежду и аксессуары для питомца",
            fontSize = 14.sp, // 0.875rem
            color = Color(0xFF3A3A3A) // заменить на точный oklch конвертированный
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(achievements) { achievement ->
                AchievementCard(
                    achievement = achievement,
                    onClaimReward = { viewModel.claimReward(achievement.id) }
                )
            }
        }
    }
}
