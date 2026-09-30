package com.example.pokeapi.data.remote

import com.example.pokeapi.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.pokeapi.data.model.PokemonDetail
import retrofit2.http.Path

interface PokemonApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetail
}