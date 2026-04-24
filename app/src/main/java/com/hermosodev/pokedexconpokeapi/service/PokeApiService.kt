package com.hermosodev.pokedexconpokeapi.service

import com.hermosodev.pokedexconpokeapi.model.PokemonDetail
import com.hermosodev.pokedexconpokeapi.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    // Lista de pokemon con su paginacion
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int

    ): PokemonListResponse

    //Detalles de un pokemon por su ID
    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: Int

    ): PokemonDetail

}
