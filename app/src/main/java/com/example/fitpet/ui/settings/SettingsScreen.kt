package com.example.fitpet.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.fitpet.ui.settings.components.*

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val settings by viewModel.settingsFlow.collectAsState(initial = null)

    if (settings == null) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
    settings?.let { currentSettings ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ===== Заголовок =====
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .background(Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("⚙", fontSize = 32.sp)
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Настройки",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Text(
                text = "Настройте приложение под себя",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(24.dp))

            // ===== Основные настройки =====
            Text(
                "Основные",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )

            // Уведомления + время
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    SettingSwitch(
                        title = "Уведомления",
                        checked = currentSettings.notifications,
                        onCheckedChange = {
                            viewModel.updateSettings(
                                currentSettings.copy(notifications = it))
                        }
                    )

                }
            }
            if (currentSettings.notifications) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Время напоминаний")
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Получаем список времен и добавляем пустое поле если нужно
                        val filledTimes = currentSettings.notificationTimes.filter { it.isNotBlank() }
                        val maxFields = 5
                        val timesToDisplay = filledTimes.toMutableList().apply {
                            // Добавляем пустое поле если не достигнут максимум (n+1 полей)
                            if (size < maxFields) {
                                add("")
                            }
                        }
                        
                        timesToDisplay.forEachIndexed { index, time ->
                            val isFilled = index < filledTimes.size
                            TimePickerRow(
                                time = if (isFilled) time else "Выберите время",
                                onTimeChange = { newTime ->
                                    val updatedTimes = filledTimes.toMutableList()
                                    if (isFilled) {
                                        // Обновляем существующее время
                                        updatedTimes[index] = newTime
                                    } else {
                                        // Добавляем новое время
                                        updatedTimes.add(newTime)
                                    }
                                    viewModel.updateSettings(
                                        currentSettings.copy(notificationTimes = updatedTimes)
                                    )
                                },
                                onRemove = if (isFilled && filledTimes.size > 1) {
                                    {
                                        val updatedTimes = filledTimes.toMutableList()
                                        updatedTimes.removeAt(index)
                                        viewModel.updateSettings(
                                            currentSettings.copy(notificationTimes = updatedTimes)
                                        )
                                    }
                                } else null
                            )
                        }
                    }
                }
            }

            // Тёмная тема
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    SettingSwitch(
                        title = "Тёмная тема",
                        checked = currentSettings.darkMode,
                        onCheckedChange = {
                            viewModel.updateSettings(currentSettings.copy(darkMode = it))
                        }
                    )
                }
            }

            // Озвучивание упражнений
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    SettingSwitch(
                        title = "Озвучивание упражнений",
                        checked = currentSettings.sound,
                        onCheckedChange = {
                            viewModel.updateSettings(currentSettings.copy(sound = it))
                        }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // ===== Параметры для расчета калорий =====
            Text(
                "Параметры для расчёта калорий",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )

            // Вес
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
            ) {
                WeightHeightRow(
                    label = "Вес",
                    value = currentSettings.weight,
                    min = 40,
                    max = 200,
                    unit = "кг",
                    onChange = {
                        viewModel.updateSettings(currentSettings.copy(weight = it))
                    }
                )
            }

            // Рост
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
            ) {
                WeightHeightRow(
                    label = "Рост",
                    value = currentSettings.height,
                    min = 140,
                    max = 220,
                    unit = "см",
                    onChange = {
                        viewModel.updateSettings(currentSettings.copy(height = it))
                    }
                )
            }

            Spacer(Modifier.height(16.dp))

            // Пол
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Пол", fontSize = 14.sp, color = Color.Gray)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        GenderSelector(
                            selected = currentSettings.gender,
                            onSelect = {
                                viewModel.updateSettings(currentSettings.copy(gender = it))
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // ===== Информация =====
            Text(
                "Информация",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
            ) {
                Text(
                    text = "О приложении\nВерсия 1.0.0",
                    modifier = Modifier.padding(12.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            // ===== Подвал =====
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
            Spacer(Modifier.height(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("FitPet © 2025", fontSize = 12.sp, color = Color.Gray)
                Text("Мотивация через заботу о питомце", fontSize = 12.sp, color = Color.LightGray)
            }

            Spacer(Modifier.height(64.dp)) // отступ для NavBar
        }
    }
}}