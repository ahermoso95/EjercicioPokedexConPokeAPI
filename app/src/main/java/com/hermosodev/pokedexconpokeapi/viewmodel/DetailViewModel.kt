package com.hermosodev.pokedexconpokeapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosodev.pokedexconpokeapi.model.PokemonDetail
import com.hermosodev.pokedexconpokeapi.repository.PokemonRepository
import kotlinx.coroutines.launch

/**
 * El ViewModel para la pantalla de detalle.
 * Gestiona la carga de la información completa de un solo Pokemon.
 */
class DetailViewModel : ViewModel() {

    private val repository = PokemonRepository()

    // Variable para contolar los detalles del pokemon
    private val _pokemonDetail = MutableLiveData<PokemonDetail?>()
    val pokemonDetail: LiveData<PokemonDetail?> get() = _pokemonDetail

    // Variable para controlar si está cargando
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Variable para controlar si hay un error
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError

    // Función para cargar los detalles desde la API usando el ID
    fun fetchPokemonDetail(id: Int) {
        _isLoading.value = true
        _isError.value = false
        
        viewModelScope.launch {
            try {
                val detail = repository.getPokemonDetail(id)
                _pokemonDetail.value = detail
            } catch (e: Exception) {
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}
