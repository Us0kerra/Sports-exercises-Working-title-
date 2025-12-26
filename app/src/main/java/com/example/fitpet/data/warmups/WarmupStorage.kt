package com.example.fitpet.data.warmups

import com.example.fitpet.R

object WarmupStorage {

    fun getWarmups(): List<Warmup> = listOf(
        Warmup(
            id = "warmup_1",
            title = "Общая разминка",
            type = WarmupType.GENERAL,
            durationMinutes = 15,
            exercisesCount = 3,
            difficulty = Difficulty.BEGINNER,
            imageRes = R.drawable.warmups_24dp,
            exercises = listOf(
                Exercise(
                    id = "run",
                    name = "Бег на месте",
                    imageRes = R.drawable.fast_run,
                    durationSeconds = 10,
                    restSeconds = 3,
                    description = ""
                ),
                Exercise(
                    id = "jumping_jacks",
                    name = "Прыжки с хлопками",
                    imageRes = R.drawable.run,
                    repetitions = 15,
                    restSeconds = 5,
                    description = ""
                ),
                Exercise(
                    id = "squats",
                    name = "Приседания",
                    imageRes = R.drawable.run,
                    repetitions = 20,
                    restSeconds = 5,
                    description = ""
                )
            )
        )
    )

    fun getWarmupById(id: String): Warmup? =
        getWarmups().firstOrNull { it.id == id }
}
