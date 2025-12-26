package com.example.fitpet.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.data.statistics.StatisticsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

class StatisticsViewModel : ViewModel() {

    private val _state = MutableStateFlow(StatisticsState())
    val state = _state.asStateFlow()

    init {
        loadStatisticsForMonth(YearMonth.now())
    }

    fun onDateSelected(date: LocalDate) {
        _state.update { it.copy(selectedDate = date) }
    }

    fun onMonthChanged(month: YearMonth) {
        loadStatisticsForMonth(month)
    }

    private fun loadStatisticsForMonth(month: YearMonth) {
        viewModelScope.launch {
            val stats = StatisticsRepository.getStatisticsForMonth(month)
            _state.update { currentState ->
                val currentSelectedDay = currentState.selectedDate.dayOfMonth
                val lastDayOfNewMonth = month.lengthOfMonth()
                val newDay = minOf(currentSelectedDay, lastDayOfNewMonth)
                val newSelectedDate = month.atDay(newDay)
                stats.copy(selectedDate = newSelectedDate)
            }
        }
    }
}
