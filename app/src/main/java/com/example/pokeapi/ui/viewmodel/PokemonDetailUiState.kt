package com.example.pokeapi.ui.viewmodel

import com.example.pokeapi.data.model.PokemonDetail

sealed class PokemonDetailUiState {

    object Idle : PokemonDetailUiState()

    object Loading : PokemonDetailUiState()

    data class Success(
        val pokemon: PokemonDetail
    ) : PokemonDetailUiState()

    data class Error(
        val message: String
    ) : PokemonDetailUiState()
}