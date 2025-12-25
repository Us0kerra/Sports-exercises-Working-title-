package com.example.fitpet.ui.achievements

import androidx.lifecycle.ViewModel
import com.example.fitpet.data.achievements.Achievement
import com.example.fitpet.data.achievements.AchievementsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AchievementsViewModel : ViewModel() {

    private val repository = AchievementsRepository

    private val _achievements = MutableStateFlow<List<Achievement>>(emptyList())
    val achievements: StateFlow<List<Achievement>> = _achievements.asStateFlow()

    init {
        loadAchievements()
    }

    private fun loadAchievements() {
        _achievements.value = repository.getAchievements()
    }

    fun claimReward(id: Int) {
        repository.claimAchievement(id)
        loadAchievements()
    }
}
