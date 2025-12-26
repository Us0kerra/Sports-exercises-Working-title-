package com.example.fitpet.data.warmups

import com.example.fitpet.R

class WarmupRepository {

    fun getWarmups(): List<Warmup>{
        return listOf(
        Warmup(
            id = "1",
            title = "Утренняя разминка",
            type = WarmupType.GENERAL,
            durationMinutes = 10,
            exercisesCount = 6,
            difficulty = Difficulty.BEGINNER,
            imageRes = R.drawable.ic_pet_black_24dp,
            exercises = listOf()
        ),
        Warmup(
            id = "2",
            title = "Разминка для шеи",
            type = WarmupType.NECK,
            durationMinutes = 7,
            exercisesCount = 5,
            difficulty = Difficulty.MEDIUM,
            imageRes = R.drawable.ic_pet_black_24dp,
            exercises = listOf()
        )
    )
}
    companion object {
        private val warmups = WarmupRepository().getWarmups()

        fun getWarmupById(id: String?): Warmup? {
            return warmups.find { it.id == id }
        }
    }
}
