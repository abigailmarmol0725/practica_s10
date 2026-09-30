package com.example.pokeapi.data.repository

import com.example.pokeapi.data.model.PokemonDetail
import com.example.pokeapi.data.model.PokemonListResponse
import com.example.pokeapi.data.remote.RetrofitInstance

class PokemonRepository {

    suspend fun getPokemonList(): PokemonListResponse {
        return RetrofitInstance.api.getPokemonList()
    }

    suspend fun getPokemonDetail(name: String): PokemonDetail {
        return RetrofitInstance.api.getPokemonDetail(name)
    }
}