package com.example.fitpet.ui.warmups.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// Если используешь Image
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpet.data.warmups.Exercise
import com.example.fitpet.ui.warmups.WarmupsViewModel

import androidx.compose.ui.graphics.Color
@Composable
fun ExerciseScreen(
    viewModel: WarmupsViewModel,
    navController: NavController
) {
    // Берём выбранное упражнение из ViewModel
    val exercise = viewModel.selectedExercise ?: run {
        // Если вдруг null, показываем сообщение
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Выберите упражнение", fontSize = 16.sp, color = Color.Gray)
        }
        return
    }

    // Состояния таймера
    var isRunning by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(exercise.durationSeconds) }

    // Логика отсчёта времени
    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000)
            timeLeft -= 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Кнопка закрытия
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.Close, contentDescription = "Закрыть")
        }

        Spacer(Modifier.height(16.dp))

        // Название упражнения
        Text(
            text = exercise.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(Modifier.height(8.dp))

        // Описание упражнения
        Text(
            text = exercise.description,
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        Spacer(Modifier.height(16.dp))

        // Таймер
        TimerComposable(
            timeLeft = timeLeft,
            isRunning = isRunning,
            onToggle = { isRunning = !isRunning }
        )

        Spacer(Modifier.height(16.dp))

        // Отображение повторений и отдыха
        Text(
            text = "Повторения: ${exercise.repetitions} • Отдых: ${exercise.restSeconds} сек",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun TimerComposable(
    timeLeft: Int,
    isRunning: Boolean,
    onToggle: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${timeLeft}s",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9810FA)
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = onToggle) {
            Text(if (isRunning) "Пауза" else "Старт")
        }
    }
}
