package com.example.fitpet.ui.warmups

import androidx.lifecycle.ViewModel
import com.example.fitpet.data.warmups.Warmup
import com.example.fitpet.data.warmups.WarmupRepository
import com.example.fitpet.data.warmups.WarmupType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WarmupsViewModel() : ViewModel() {
    private val repository = WarmupRepository()

    private val _selectedType = MutableStateFlow(WarmupType.ALL)
    val selectedType: StateFlow<WarmupType> = _selectedType.asStateFlow()

    private val _warmups = MutableStateFlow<List<Warmup>>(emptyList())
    val warmups: StateFlow<List<Warmup>> = _warmups.asStateFlow()

    init {
        _warmups.value = repository.getWarmups() // <-- если метод обычный List
    }

    fun selectType(type: WarmupType) {
        _selectedType.value = type
    }
}