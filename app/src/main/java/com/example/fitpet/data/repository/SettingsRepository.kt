package com.example.fitpet.data.repository

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.fitpet.data.model.Gender
import com.example.fitpet.data.model.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "fitness_pet"
)

object SettingsRepository {
    private val NOTIFICATIONS = booleanPreferencesKey("notifications")
    private val SOUND = booleanPreferencesKey("sound")
    private val WEIGHT = intPreferencesKey("weight")
    private val HEIGHT = intPreferencesKey("height")
    private val GENDER = stringPreferencesKey("gender")
    private val TIME = stringPreferencesKey("time")
    suspend fun save(context: Context, settings: UserSettings) {
        context.dataStore.edit {
            it[NOTIFICATIONS] = settings.notifications
            it[SOUND] = settings.sound
            it[WEIGHT] = settings.weight
            it[HEIGHT] = settings.height
            it[GENDER] = settings.gender.name
            it[TIME] = settings.notificationTime
        }
    }
    fun load(context: Context): Flow<UserSettings> =
        context.dataStore.data.map {
            UserSettings(
                notifications = it[NOTIFICATIONS] ?: true,
                sound = it[SOUND] ?: true,
                weight = it[WEIGHT] ?: 70,
                height = it[HEIGHT] ?: 175,
                gender = Gender.valueOf(it[GENDER] ?: "MALE"),
                notificationTime = it[TIME] ?: "09:00"
            )
        }
}