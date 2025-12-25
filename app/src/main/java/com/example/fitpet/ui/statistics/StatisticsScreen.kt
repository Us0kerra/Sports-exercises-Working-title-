package com.example.fitpet.ui.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitpet.ui.statistics.components.Calendar
import com.example.fitpet.ui.statistics.components.StatsRow
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StatisticsScreen(
    statisticsViewModel: StatisticsViewModel = viewModel()
) {
    val state by statisticsViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Статистика сегодня",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7C3AED)
        )

        Text(
            text = "Ваш ежедневный прогресс",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(16.dp))

        StatsRow(state)

        Spacer(Modifier.height(24.dp))

        Calendar(
            completedDays = state.completedDays,
            selectedDay = state.selectedDay,
            streak = state.streak,
            onDayClick = statisticsViewModel::onDaySelected

        )


        Spacer(Modifier.height(64.dp)) // под NavBar
    }
}
