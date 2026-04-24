package com.hermosodev.pokedexconpokeapi.repository

import com.hermosodev.pokedexconpokeapi.model.PokemonDetail
import com.hermosodev.pokedexconpokeapi.model.PokemonListResponse
import com.hermosodev.pokedexconpokeapi.service.RetrofitClient

//uso un repositorio para gestionar los datos y lo pueda comunicar con el viewmodel
class PokemonRepository {

    private val api = RetrofitClient.instance

    // Función para obtener la lista de pokemon
    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        return api.getPokemonList(limit, offset)
    }

    //Funcion para obtener el detalle de un pokemon
    suspend fun getPokemonDetail(id: Int): PokemonDetail {
        return api.getPokemonDetail(id)
    }

}
