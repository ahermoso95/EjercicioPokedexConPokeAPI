package com.hermosodev.pokedexconpokeapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosodev.pokedexconpokeapi.model.PokemonBasicInfo
import com.hermosodev.pokedexconpokeapi.repository.PokemonRepository
import kotlinx.coroutines.launch

/**
 * El ViewModel es el "cerebro" de la pantalla.
 * Se encarga de pedir los datos y guardarlos para que la actividad los muestre.
 * Así, si giras el móvil, los datos no se pierden.
 */
class MainViewModel : ViewModel() {

    private val repository = PokemonRepository()

    // Estas variables LiveData avisan a la Activity cuando algo cambia
    private val _pokemonList = MutableLiveData<List<PokemonBasicInfo>>()
    val pokemonList: LiveData<List<PokemonBasicInfo>> get() = _pokemonList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _hasError = MutableLiveData<Boolean>()
    val hasError: LiveData<Boolean> get() = _hasError

    // Lógica para la paginación
    private var currentOffset = 0
    private val limit = 20
    var currentPage = 1
        private set

    // Función que se llama al empezar o al darle a reintentar
    fun loadPokemon() {
        // Marcamos que estamos cargando y que no hay error de momento
        _isLoading.value = true
        _hasError.value = false

        // Lanzamos una corrutina (un hilo secundario) para no bloquear la pantalla
        viewModelScope.launch {
            try {
                // Pedimos los datos al repositorio
                val response = repository.getPokemonList(limit, currentOffset)

                _pokemonList.value = response.results
            } catch (e: Exception) {
                // Si falla (por ejemplo, sin internet), marcamos error
                _hasError.value = true
            } finally {
                // Al terminar (bien o mal), quitamos el círculo de carga
                _isLoading.value = false
            }
        }
    }

    // Ir a la siguiente página
    fun nextPage() {
        currentOffset += limit
        currentPage++
        loadPokemon()
    }

    // Volver a la página anterior
    fun previousPage() {
        if (currentOffset >= limit) {
            currentOffset -= limit
            currentPage--
            loadPokemon()
        }
    }
}
