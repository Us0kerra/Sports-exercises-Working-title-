package com.example.fitpet.data.repository

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.fitpet.data.model.Gender
import com.example.fitpet.data.model.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.edit
// Делегат для DataStore
private val Context.settingsDataStore by preferencesDataStore(name = "settings")

class SettingsRepository(val context: Context) {

    private val NOTIFICATIONS_KEY = booleanPreferencesKey("notifications")
    private val SOUND_KEY = booleanPreferencesKey("sound")
    private val DARK_MODE_KEY = booleanPreferencesKey("darkMode")
    private val WEIGHT_KEY = intPreferencesKey("weight")
    private val HEIGHT_KEY = intPreferencesKey("height")
    private val GENDER_KEY = stringPreferencesKey("gender")
    private val NOTIFICATION_TIME_KEY = stringPreferencesKey("notificationTime")

    val settingsFlow: Flow<UserSettings> = context.settingsDataStore.data
        .map { prefs ->
            UserSettings(
                notifications = prefs[NOTIFICATIONS_KEY] ?: true,
                sound = prefs[SOUND_KEY] ?: true,
                darkMode = prefs[DARK_MODE_KEY] ?: false,
                weight = prefs[WEIGHT_KEY] ?: 70,
                height = prefs[HEIGHT_KEY] ?: 175,
                gender = if (prefs[GENDER_KEY] ?: "male" == "male") Gender.MALE else Gender.FEMALE,
                notificationTime = prefs[NOTIFICATION_TIME_KEY] ?: "09:00"
            )
        }

    suspend fun updateSettings(settings: UserSettings) {
        context.settingsDataStore.edit { prefs ->
            prefs[NOTIFICATIONS_KEY] = settings.notifications
            prefs[SOUND_KEY] = settings.sound
            prefs[DARK_MODE_KEY] = settings.darkMode
            prefs[WEIGHT_KEY] = settings.weight
            prefs[HEIGHT_KEY] = settings.height
            prefs[GENDER_KEY] = if (settings.gender == Gender.MALE) "male" else "female"
            prefs[NOTIFICATION_TIME_KEY] = settings.notificationTime
        }
    }
}
