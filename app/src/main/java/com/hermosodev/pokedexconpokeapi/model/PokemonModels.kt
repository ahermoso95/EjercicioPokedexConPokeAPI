package com.hermosodev.pokedexconpokeapi.model

import com.google.gson.annotations.SerializedName

//Respuesta de la lista de pokemon
data class PokemonListResponse(
    @SerializedName("results") val results: List<PokemonBasicInfo>
)

// Datos basicos para cada un pokemon de la lista
data class PokemonBasicInfo(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    // Para sacar el ID de los pokemon de la URL
    fun getPokemonId(): Int {
        val trozos = url.split("/")
        return trozos[trozos.size - 2].toInt()
    }

    //URL para la imagen del pokemon partiendo de la id
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getPokemonId()}.png"
    }

}

// Datos completos de los atributos del pokemon para la vista de detalle que aparezca el id, nombre, peso, tipo y la imagen
data class PokemonDetail (

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sprites") val sprites: PokemonSprites,
    @SerializedName("types") val types: List<PokemonTypeSlot>
)


//Imagenes del pokemon
data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String
)

// Tipos (fuego, agua...)
data class PokemonTypeSlot(
    @SerializedName("type") val type: PokemonType
)

data class PokemonType(
    @SerializedName("name") val name: String
)
