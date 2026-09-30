package com.example.pokeapi.ui.viewmodel

import com.example.pokeapi.data.model.PokemonItem

sealed class PokemonUiState {

    object Loading : PokemonUiState()

    data class Success(
        val pokemonList: List<PokemonItem>
    ) : PokemonUiState()

    data class Error(
        val message: String
    ) : PokemonUiState()
}