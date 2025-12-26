package com.example.fitpet.ui.warmups

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitpet.data.warmups.Exercise
import com.example.fitpet.data.warmups.Warmup
import com.example.fitpet.data.warmups.WarmupRepository
import com.example.fitpet.data.warmups.WarmupType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class WarmupsViewModel : ViewModel() {
    private val repository = WarmupRepository()

    private val _selectedType = MutableStateFlow(WarmupType.ALL)
    val selectedType: StateFlow<WarmupType> = _selectedType.asStateFlow()

    private val _warmups = MutableStateFlow<List<Warmup>>(emptyList())
    val warmups: StateFlow<List<Warmup>> = _warmups.asStateFlow()

    init {
        _warmups.value = repository.getWarmups()
    }

    fun selectType(type: WarmupType) {
        _selectedType.value = type
    }
    // внутреннее состояние
    private val _selectedExercise = mutableStateOf<Exercise?>(null)

    // публичный доступ через делегат
    var selectedExercise: Exercise? by _selectedExercise
        private set

    fun selectExercise(exercise: Exercise) {
        _selectedExercise.value = exercise
    }
}