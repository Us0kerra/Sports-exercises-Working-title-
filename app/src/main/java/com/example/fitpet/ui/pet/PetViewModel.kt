package com.example.fitpet.ui.pet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpet.data.Pets.PetType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PetViewModel : ViewModel() {

    private val _petName = MutableStateFlow("")
    val petName: StateFlow<String> = _petName

    private val _petType = MutableStateFlow<PetType?>(null)
    val petType: StateFlow<PetType?> = _petType

    fun onNameChange(name: String) {
        _petName.value = name
    }

    fun onPetSelected(type: PetType) {
        _petType.value = type
    }

    fun createPet() {
        val name = _petName.value
        val type = _petType.value

        if (name.isBlank() || type == null) return

        // TODO:
        // - сохранить питомца
        // - навигация
        // - аналитика
    }
}
