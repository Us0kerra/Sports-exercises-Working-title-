package com.example.fitpet.ui.warmups

import androidx.lifecycle.ViewModel
import com.example.fitpet.data.warmups.Exercise
import com.example.fitpet.data.warmups.Warmup
import com.example.fitpet.data.warmups.WarmupRepository
import com.example.fitpet.data.warmups.WarmupType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WarmupsViewModel(private val repository: WarmupRepository = WarmupRepository()) : ViewModel() {


    private val _selectedType = MutableStateFlow(WarmupType.ALL)
    val selectedType: StateFlow<WarmupType> = _selectedType.asStateFlow()

    private val _warmup = MutableStateFlow<Warmup?>(null)
    val warmup: StateFlow<Warmup?> = _warmup

    fun loadWarmup(id: String) {
        _warmup.value = repository.getWarmup(id)
    }

    fun getExercise(index: Int): Exercise? =
        _warmup.value?.exercises?.getOrNull(index)


    fun selectType(type: WarmupType) {
        _selectedType.value = type
    }
}