package com.example.pokeapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val repository = PokemonRepository()

    // Estado de la lista
    private val _uiState =
        MutableStateFlow<PokemonUiState>(PokemonUiState.Loading)

    val uiState: StateFlow<PokemonUiState> =
        _uiState.asStateFlow()

    // Estado del detalle
    private val _detailState =
        MutableStateFlow<PokemonDetailUiState>(PokemonDetailUiState.Idle)

    val detailState: StateFlow<PokemonDetailUiState> =
        _detailState.asStateFlow()

    init {
        loadPokemon()
    }

    fun loadPokemon() {

        _uiState.value = PokemonUiState.Loading

        viewModelScope.launch {
            try {

                val response = repository.getPokemonList()

                _uiState.value =
                    PokemonUiState.Success(response.results)

            } catch (e: Exception) {

                _uiState.value =
                    PokemonUiState.Error(
                        e.message ?: "Error al cargar los Pokémon"
                    )
            }
        }
    }

    fun loadPokemonDetail(name: String) {

        _detailState.value = PokemonDetailUiState.Loading

        viewModelScope.launch {
            try {

                val pokemon = repository.getPokemonDetail(name)

                _detailState.value =
                    PokemonDetailUiState.Success(pokemon)

            } catch (e: Exception) {

                _detailState.value =
                    PokemonDetailUiState.Error(
                        e.message ?: "Error al cargar el detalle"
                    )
            }
        }
    }
    fun closeDetail() {
        _detailState.value = PokemonDetailUiState.Idle
    }

}
