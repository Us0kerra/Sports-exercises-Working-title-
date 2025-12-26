package com.example.fitpet

import android.app.Application
import com.example.fitpet.data.PetRepository
import com.example.fitpet.data.PetRepositoryImpl

class FitPetApplication : Application() {
    val petRepository: PetRepository by lazy {
        PetRepositoryImpl(this)
    }
}