package com.hermosodev.pokedexconpokeapi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.hermosodev.pokedexconpokeapi.databinding.ActivityMainBinding
import com.hermosodev.pokedexconpokeapi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    
    // El ViewModel para manejar los datos
    private val viewModel: MainViewModel by viewModels()
    
    //Adaptador para poder usar la lista de pokemon
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //preparamos el RecyclerView y los botones
        configurarLista()
        configurarBotones()
        verDatos()

        // Cargamos los datos por primera vez
        viewModel.loadPokemon()
    }


    private fun configurarLista() {
        // Al pulsar un pokemon vamos a la vist de detalle
        adapter = PokemonAdapter(emptyList()) { p ->
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("POKEMON_ID", p.getPokemonId())
            startActivity(i)
        }
        
        binding.rvPokemon.adapter = adapter
        binding.rvPokemon.layoutManager = GridLayoutManager(this, 2)
    }

    private fun configurarBotones() {
        // Boton para ir adelante
        binding.btnNext.setOnClickListener {
            viewModel.nextPage()
        }

        //boton para ir atras
        binding.btnPrevious.setOnClickListener {
            viewModel.previousPage()
        }

        //Boton por si falla el internet y queremos reintentar
        binding.btnRetry.setOnClickListener {
            viewModel.loadPokemon()
        }
    }

    private fun verDatos() {
        // Cuando cambie la lista de pokemon
        viewModel.pokemonList.observe(this) { lista ->
            adapter.actualizar(lista)

            //para enumerar la pagina actual
            binding.tvPageNumber.text = "Pagina ${viewModel.currentPage}"
        }

        // Condicion para mostrar el progreso de carga
        viewModel.isLoading.observe(this) { cargando ->

            if (cargando) {
                binding.progressBar.visibility = View.VISIBLE
                binding.rvPokemon.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.rvPokemon.visibility = View.VISIBLE
            }
        }

        // Por si hay un error de red
        viewModel.hasError.observe(this) { error ->
            if (error) {
                binding.layoutError.visibility = View.VISIBLE
                binding.rvPokemon.visibility = View.GONE

            } else {
                binding.layoutError.visibility = View.GONE
            }
        }

    }

}
