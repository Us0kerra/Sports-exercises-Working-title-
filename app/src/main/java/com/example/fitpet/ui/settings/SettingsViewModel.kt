package com.example.fitpet.ui.settings

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.data.model.UserSettings
import com.example.fitpet.data.repository.SettingsRepository
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val context: Context
) : ViewModel() {
    var settings by mutableStateOf(UserSettings())
        private set
    init {
        viewModelScope.launch {
            SettingsRepository.load(context).collect {
                settings = it
            }
        }
    }
    fun update(update: UserSettings) {
        settings = update
        viewModelScope.launch {
            SettingsRepository.save(context, update)
        }
    }
}