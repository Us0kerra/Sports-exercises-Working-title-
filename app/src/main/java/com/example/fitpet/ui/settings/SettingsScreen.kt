package com.example.fitpet.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.fitpet.ui.settings.components.*



@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val settings = viewModel.settings
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Настройки",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(24.dp))
        SettingSwitch(
            title = "Уведомления",
            checked = settings.notifications,
            onCheckedChange = {
                viewModel.update(settings.copy(notifications = it))
            }
        )
        if (settings.notifications) {
            TimePickerRow(
                time = settings.notificationTime,
                onTimeChange = {
                    viewModel.update(settings.copy(notificationTime = it))
                }
            )
        }
        SettingSwitch(
            title = "Озвучивание упражнений",
            checked = settings.sound,
            onCheckedChange = {
                viewModel.update(settings.copy(sound = it))
            }
        )
        WeightHeightRow(
            label = "Вес",
            value = settings.weight,
            min = 40,
            max = 200,
            unit = "кг",
            onChange = {
                viewModel.update(settings.copy(weight = it))
            }
        )
        GenderSelector(
            selected = settings.gender,
            onSelect = {
                viewModel.update(settings.copy(gender = it))
            }
        )
    }
}