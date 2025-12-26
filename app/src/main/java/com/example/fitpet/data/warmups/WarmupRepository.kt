package com.example.fitpet.data.warmups

import com.example.fitpet.R

class WarmupRepository {

    fun getWarmups(): List<Warmup> {
        return warmups
    }

    companion object {
        private val exercises = listOf(
            Exercise(
                id = "1",
                name = "Растяжка шеи",
                description = "Аккуратно наклоняйте голову из стороны в сторону.",
                durationSeconds = 30,
                repetitions = 5,
                imageRes = R.drawable.cat_base,
                restSeconds = 10,
                caloriesPerMinute = 3
            ),
            Exercise(
                id = "2",
                name = "Вращение плеч",
                description = "Вращайте плечи вперёд и назад.",
                durationSeconds = 30,
                repetitions = 10,
                imageRes = R.drawable.cat_base,
                restSeconds = 10,
                caloriesPerMinute = 4
            ),
            Exercise(
                id = "3",
                name = "Растяжка 'Кошка-Корова'",
                description = "Чередуйте прогиб и округление спины.",
                durationSeconds = 60,
                repetitions = 10,
                imageRes = R.drawable.cat_base,
                restSeconds = 10,
                caloriesPerMinute = 5
            ),
            Exercise(
                id = "4",
                name = "Downward-Facing Dog",
                description = "A full-body stretch that targets your hamstrings, calves, and shoulders.",
                durationSeconds = 60,
                repetitions = 3,
                imageRes = R.drawable.cat_base,
                restSeconds = 15,
                caloriesPerMinute = 6
            ),
            Exercise(
                id = "5",
                name = "Jumping Jacks",
                description = "A classic cardio exercise to get your heart rate up.",
                durationSeconds = 60,
                repetitions = 20,
                imageRes = R.drawable.cat_base,
                restSeconds = 15,
                caloriesPerMinute = 10
            ),
            Exercise(
                id = "6",
                name = "High Knees",
                description = "A great way to warm up your legs and core.",
                durationSeconds = 60,
                repetitions = 20,
                imageRes = R.drawable.cat_base,
                restSeconds = 15,
                caloriesPerMinute = 9
            ),
            Exercise(
                id = "7",
                name = "Butt Kicks",
                description = "A running-in-place exercise that stretches your quads.",
                durationSeconds = 60,
                repetitions = 20,
                imageRes = R.drawable.cat_base,
                restSeconds = 15,
                caloriesPerMinute = 8
            ),
            Exercise(
                id = "8",
                name = "Bodyweight Squats",
                description = "A fundamental strength exercise that targets your entire lower body.",
                durationSeconds = 60,
                repetitions = 15,
                imageRes = R.drawable.cat_base,
                restSeconds = 20,
                caloriesPerMinute = 7
            ),
            Exercise(
                id = "9",
                name = "Lunges",
                description = "A great exercise for your quads, glutes, and hamstrings.",
                durationSeconds = 60,
                repetitions = 10,
                imageRes = R.drawable.cat_base,
                restSeconds = 20,
                caloriesPerMinute = 8
            ),
            Exercise(
                id = "10",
                name = "Leg Swings",
                description = "A dynamic stretch to warm up your hips and hamstrings.",
                durationSeconds = 30,
                repetitions = 10,
                imageRes = R.drawable.cat_base,
                restSeconds = 10,
                caloriesPerMinute = 4
            )
        )

        private val warmups = listOf(
            Warmup(
                id = "1",
                name = "Разминка для всего тела",
                description = "Полный комплекс растяжки для всего тела.",
                type = WarmupType.GENERAL,
                exercises = exercises.slice(0..3)
            ),
            Warmup(
                id = "2",
                name = "Quick Cardio",
                description = "A short cardio session to get your heart pumping.",
                type = WarmupType.HANDS,
                exercises = exercises.slice(4..6)
            ),
            Warmup(
                id = "3",
                name = "Leg Day Warm-up",
                description = "A warm-up focused on preparing your legs for a workout.",
                type = WarmupType.LEGS,
                exercises = exercises.slice(7..9)
            )
        )

        fun getWarmupById(id: String?): Warmup? {
            return warmups.find { it.id == id }
        }
    }
}