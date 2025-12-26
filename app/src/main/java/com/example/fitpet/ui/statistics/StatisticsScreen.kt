package com.example.fitpet.ui.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpet.ui.statistics.components.Calendar
import com.example.fitpet.ui.statistics.components.StatsRow

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
            text = state.title,
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
            state = state,
            onDayClick = statisticsViewModel::onDateSelected,
            onMonthChanged = statisticsViewModel::onMonthChanged
        )

        Spacer(Modifier.height(64.dp)) // под NavBar
    }
}
