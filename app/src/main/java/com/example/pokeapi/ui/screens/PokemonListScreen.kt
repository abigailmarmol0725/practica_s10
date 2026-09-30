package com.example.pokeapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapi.ui.viewmodel.PokemonUiState
import com.example.pokeapi.ui.viewmodel.PokemonViewModel
import androidx.compose.foundation.clickable
import com.example.pokeapi.ui.viewmodel.PokemonDetailUiState

@Composable
fun PokemonListScreen(
    pokemonViewModel: PokemonViewModel = viewModel()
) {

    val uiState by pokemonViewModel.uiState.collectAsState()
    val detailState by pokemonViewModel.detailState.collectAsState()

    if (detailState !is PokemonDetailUiState.Idle) {

        PokemonDetailScreen(
            detailState = detailState,
            onBack = {
                pokemonViewModel.closeDetail()
            }
        )

        return
    }

    when (val state = uiState) {

        is PokemonUiState.Loading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is PokemonUiState.Success -> {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(state.pokemonList) { pokemon ->

                    Text(
                        text = pokemon.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                pokemonViewModel.loadPokemonDetail(pokemon.name)
                            }
                            .padding(12.dp)
                    )
                }
            }
        }

        is PokemonUiState.Error -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Error: ${state.message}"
                    )

                    Button(
                        onClick = {
                            pokemonViewModel.loadPokemon()
                        }
                    ) {
                        Text("Reintentar")
                    }
                }
            }
        }
    }
}