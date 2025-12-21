package com.example.fitpet.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
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
                        TimePickerRow(
                            time = currentSettings.notificationTime,
                            onTimeChange = {
                                viewModel.updateSettings(currentSettings.copy(notificationTime = it))
                            }
                        )
                    }
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