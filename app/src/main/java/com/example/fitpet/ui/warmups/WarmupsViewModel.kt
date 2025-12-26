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

    // Состояние текущей программы тренировок
    private val _currentWarmup = mutableStateOf<Warmup?>(null)
    var currentWarmup: Warmup? by _currentWarmup
        private set

    // Индекс текущего упражнения в программе
    private val _currentExerciseIndex = mutableStateOf(0)
    var currentExerciseIndex: Int by _currentExerciseIndex
        private set

    // Начать программу тренировок
    fun startWarmup(warmup: Warmup) {
        _currentWarmup.value = warmup
        _currentExerciseIndex.value = 0
        if (warmup.exercises.isNotEmpty()) {
            _selectedExercise.value = warmup.exercises[0]
        }
    }

    // Начать программу тренировок с определенного упражнения
    fun startWarmupFromExercise(warmup: Warmup, exercise: Exercise) {
        _currentWarmup.value = warmup
        val exerciseIndex = warmup.exercises.indexOf(exercise)
        _currentExerciseIndex.value = if (exerciseIndex >= 0) exerciseIndex else 0
        _selectedExercise.value = if (exerciseIndex >= 0) exercise else warmup.exercises.firstOrNull()
    }

    // Перейти к следующему упражнению
    fun moveToNextExercise(): Boolean {
        val warmup = _currentWarmup.value ?: return false
        val nextIndex = _currentExerciseIndex.value + 1
        
        if (nextIndex < warmup.exercises.size) {
            _currentExerciseIndex.value = nextIndex
            _selectedExercise.value = warmup.exercises[nextIndex]
            return true
        }
        return false
    }

    // Сбросить состояние тренировки
    fun resetWorkout() {
        _currentWarmup.value = null
        _currentExerciseIndex.value = 0
        _selectedExercise.value = null
    }
}