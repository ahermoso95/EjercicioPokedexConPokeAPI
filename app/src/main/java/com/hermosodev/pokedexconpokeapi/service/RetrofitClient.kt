package com.hermosodev.pokedexconpokeapi.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    //Creo el cliente de la libreria Retrofit para que pueda usarse en la aplicacion y usando el endpoint de PokeApi

    val instance: PokeApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PokeApiService::class.java)
    }
}
