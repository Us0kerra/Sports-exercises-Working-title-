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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

// Если используешь Image
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpet.data.warmups.Exercise
import com.example.fitpet.data.repository.SettingsRepository
import com.example.fitpet.data.statistics.StatisticsRepository
import com.example.fitpet.ui.warmups.WarmupsViewModel

import androidx.compose.ui.graphics.Color

enum class ExerciseState {
    EXERCISE, REST, COMPLETED
}

@Composable
fun ExerciseScreen(
    viewModel: WarmupsViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val settingsRepository = remember { SettingsRepository(context) }
    
    // Берём выбранное упражнение из ViewModel
    val exercise = viewModel.selectedExercise ?: run {
        // Если вдруг null, показываем сообщение
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Выберите упражнение", fontSize = 16.sp, color = Color.Gray)
        }
        return
    }

    val currentWarmup = viewModel.currentWarmup
    val isInWorkout = currentWarmup != null

    // Состояния таймера
    var isRunning by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(exercise.durationSeconds) }
    var state by remember { mutableStateOf(ExerciseState.EXERCISE) }
    var restTimeLeft by remember { mutableStateOf(5) }

    // Логика отсчёта времени упражнения
    LaunchedEffect(isRunning, state) {
        if (state == ExerciseState.EXERCISE && isRunning && timeLeft > 0) {
            while (isRunning && timeLeft > 0 && state == ExerciseState.EXERCISE) {
                delay(1000)
                timeLeft -= 1
            }
            // Таймер упражнения закончился
            if (timeLeft == 0 && state == ExerciseState.EXERCISE) {
                if (isInWorkout) {
                    // Запускаем перерыв
                    state = ExerciseState.REST
                    restTimeLeft = 5
                    isRunning = true
                }
            }
        }
    }

    // Логика отсчёта времени перерыва
    LaunchedEffect(state, isRunning) {
        if (state == ExerciseState.REST && isRunning && restTimeLeft > 0) {
            while (isRunning && restTimeLeft > 0 && state == ExerciseState.REST) {
                delay(1000)
                restTimeLeft -= 1
            }
            // Перерыв закончился
            if (restTimeLeft == 0 && state == ExerciseState.REST) {
                val hasNext = viewModel.moveToNextExercise()
                if (hasNext) {
                    // Переходим к следующему упражнению
                    val nextExercise = viewModel.selectedExercise
                    if (nextExercise != null) {
                        timeLeft = nextExercise.durationSeconds
                        state = ExerciseState.EXERCISE
                        isRunning = false
                    }
                } else {
                    // Тренировка завершена
                    state = ExerciseState.COMPLETED
                    isRunning = false
                }
            }
        }
    }

    // Рассчитываем и записываем статистику при завершении тренировки
    LaunchedEffect(state) {
        if (state == ExerciseState.COMPLETED) {
            val settings = settingsRepository.settingsFlow.first()
            val warmup = viewModel.currentWarmup
            
            if (warmup != null) {
                // Рассчитываем общее количество минут для всех упражнений программы
                // Согласно требованиям, учитываем все упражнения программы
                val totalMinutes = warmup.exercises.sumOf { it.durationSeconds } / 60.0
                val totalMinutesInt = totalMinutes.toInt()
                
                // Рассчитываем калории
                var totalCalories = 0.0
                warmup.exercises.forEach { ex ->
                    val minutes = ex.durationSeconds / 60.0
                    val baseCalories = ex.caloriesPerMinute * minutes
                    
                    // Учитываем вес, рост и пол
                    val bmrMultiplier = when (settings.gender) {
                        com.example.fitpet.data.model.Gender.MALE -> {
                            // Формула Миффлина-Сан Жеора для мужчин
                            10 * settings.weight + 6.25 * settings.height - 5 * 30 + 5
                        }
                        com.example.fitpet.data.model.Gender.FEMALE -> {
                            // Формула Миффлина-Сан Жеора для женщин
                            10 * settings.weight + 6.25 * settings.height - 5 * 30 - 161
                        }
                    }
                    val adjustedCalories = baseCalories * (bmrMultiplier / 2000.0)
                    totalCalories += adjustedCalories
                }
                
                StatisticsRepository.addWorkout(
                    calories = totalCalories.toInt(),
                    minutes = totalMinutesInt
                )
                
                // Сбрасываем состояние тренировки
                //startIndex.value = -1
            }
        }
    }

    // Сброс таймера при смене упражнения (только если не в процессе перерыва или завершения)
    LaunchedEffect(exercise.id) {
        if (state != ExerciseState.REST && state != ExerciseState.COMPLETED) {
            timeLeft = exercise.durationSeconds
            state = ExerciseState.EXERCISE
            isRunning = false
            restTimeLeft = 5
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Кнопка закрытия
        IconButton(onClick = { 
            viewModel.resetWorkout()
            navController.popBackStack() 
        }) {
            Icon(Icons.Default.Close, contentDescription = "Закрыть")
        }

        Spacer(Modifier.height(16.dp))

        when (state) {
            ExerciseState.EXERCISE -> {
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
            ExerciseState.REST -> {
                Text(
                    text = "Перерыв",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF9810FA)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "${restTimeLeft}s",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF9810FA)
                )
            }
            ExerciseState.COMPLETED -> {
                Text(
                    text = "Тренировка завершена!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF9810FA)
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = { 
                    viewModel.resetWorkout()
                    navController.popBackStack() 
                }) {
                    Text("Вернуться")
                }
            }
        }
        LaunchedEffect(state) {
            if (state == ExerciseState.COMPLETED) {
                navController.navigate("navigation_warmups") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            }
        }

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
