package com.example.fitpet.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitpet.ui.pet.PetViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pet")

interface PetRepository {
    val pet: Flow<Pet?>
    suspend fun savePet(pet: Pet)
}

class PetRepositoryImpl(private val context: Context) : PetRepository {
    override val pet: Flow<Pet?> = context.dataStore.data.map {
        val name = it[KEY_NAME]
        val level = it[KEY_LEVEL]
        val experience = it[KEY_EXP]
        val health = it[KEY_HEALTH]
        val petType = it[KEY_PET_TYPE]
        if (name == null || level == null || experience == null || health == null || petType == null) {
            null
        } else {
            Pet(name, level, experience, health, PetType.valueOf(petType))
        }
    }

    override suspend fun savePet(pet: Pet) {
        context.dataStore.edit {
            it[KEY_NAME] = pet.name
            it[KEY_LEVEL] = pet.level
            it[KEY_EXP] = pet.experience
            it[KEY_HEALTH] = pet.health
            it[KEY_PET_TYPE] = pet.petType.name
        }
    }

    companion object {
        private val KEY_NAME = stringPreferencesKey("name")
        private val KEY_LEVEL = intPreferencesKey("level")
        private val KEY_EXP = intPreferencesKey("exp")
        private val KEY_HEALTH = intPreferencesKey("health")
        private val KEY_PET_TYPE = stringPreferencesKey("pet_type")
    }
}

class PetViewModelFactory(private val repository: PetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}