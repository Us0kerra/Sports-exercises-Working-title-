package com.example.fitpet.data.warmups

import com.example.fitpet.R

class WarmupRepository {

    fun getWarmups(): List<Warmup> =
        WarmupStorage.getWarmups()

    fun getWarmup(id: String): Warmup =
        WarmupStorage.getWarmupById(id)
            ?: error("Warmup not found: $id")


    companion object {
        private val warmups = WarmupRepository().getWarmups()

        fun getWarmupById(id: String?): Warmup? {
            return warmups.find { it.id == id }
        }
    }
}



