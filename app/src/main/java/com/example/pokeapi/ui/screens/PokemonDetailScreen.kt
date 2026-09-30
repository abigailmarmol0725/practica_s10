package com.example.pokeapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokeapi.ui.viewmodel.PokemonDetailUiState

@Composable
fun PokemonDetailScreen(
    detailState: PokemonDetailUiState,
    onBack: () -> Unit
) {

    when (detailState) {

        is PokemonDetailUiState.Idle -> {
            // Todavía no se ha seleccionado un Pokémon
        }

        is PokemonDetailUiState.Loading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is PokemonDetailUiState.Success -> {

            val pokemon = detailState.pokemon

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = pokemon.name.uppercase()
                )

                Text(
                    text = "ID: ${pokemon.id}"
                )

                Text(
                    text = "Altura: ${pokemon.height}"
                )

                Text(
                    text = "Peso: ${pokemon.weight}"
                )

                Text(
                    text = "Tipo: ${
                        pokemon.types.joinToString { it.type.name }
                    }"
                )

                Button(
                    onClick = onBack
                ) {
                    Text("Volver")
                }
            }
        }

        is PokemonDetailUiState.Error -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "Error: ${detailState.message}"
                )

                Button(
                    onClick = onBack
                ) {
                    Text("Volver")
                }
            }
        }
    }
}