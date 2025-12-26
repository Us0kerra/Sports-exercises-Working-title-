package com.example.fitpet.ui.pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.data.Pet
import com.example.fitpet.data.PetRepository
import com.example.fitpet.data.PetType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PetViewModel(private val repository: PetRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(PetScreenState())
    val uiState: StateFlow<PetScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.pet.collect { pet ->
                if (pet != null) {
                    _uiState.update { it.copy(pet = pet, name = pet.name, isPetCreated = true, isLoading = false) }
                } else {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onPetSelected(type: PetType) {
        _uiState.update { it.copy(petType = type) }
    }

    fun createPet() {
        viewModelScope.launch {
            val pet = Pet(
                name = uiState.value.name,
                petType = uiState.value.petType ?: return@launch
            )
            repository.savePet(pet)
        }
    }

    fun onEditName() {
        _uiState.update { it.copy(isEditingName = true) }
    }

    fun savePetName() {
        viewModelScope.launch {
            val updatedPet = uiState.value.pet?.copy(name = uiState.value.name) ?: return@launch
            repository.savePet(updatedPet)
            _uiState.update { it.copy(isEditingName = false) }
        }
    }
}

data class PetScreenState(
    val name: String = "",
    val petType: PetType? = null,
    val pet: Pet? = null,
    val isPetCreated: Boolean = false,
    val isLoading: Boolean = true,
    val isEditingName: Boolean = false
)