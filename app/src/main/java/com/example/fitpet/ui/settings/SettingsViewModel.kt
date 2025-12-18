package com.example.fitpet.ui.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.data.model.UserSettings
import com.example.fitpet.data.repository.SettingsRepository
import com.example.fitpet.notifications.NotificationScheduler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {

    var settings by mutableStateOf(UserSettings())
        private set
    val settingsFlow = repository.settingsFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    init {
        viewModelScope.launch {
            repository.settingsFlow.collect { loadedSettings ->
                settings = loadedSettings
            }
        }
    }

    fun updateSettings  (newSettings: UserSettings) {
        settings = newSettings
        viewModelScope.launch {
            repository.updateSettings(newSettings)

            // Обновляем запланированное напоминание при изменении настроек
            if (newSettings.notifications) {
                val (hour, minute) = newSettings.notificationTime.split(":").let {
                    val h = it.getOrNull(0)?.toIntOrNull() ?: 9
                    val m = it.getOrNull(1)?.toIntOrNull() ?: 0
                    h to m
                }
                NotificationScheduler.scheduleDailyReminder(
                    context = repository.context,
                    hour = hour,
                    minute = minute
                )
            } else {
                NotificationScheduler.cancelDailyReminder(repository.context)
            }
        }
    }
}
