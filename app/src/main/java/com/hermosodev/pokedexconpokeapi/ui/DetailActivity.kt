package com.hermosodev.pokedexconpokeapi.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hermosodev.pokedexconpokeapi.databinding.ActivityDetailBinding
import com.hermosodev.pokedexconpokeapi.model.PokemonDetail
import com.hermosodev.pokedexconpokeapi.viewmodel.DetailViewModel

class DetailActivity: AppCompatActivity(){

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Boton para regresar atras en la toolbar
        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        // Pillamos el id que pasamos desde la otra pantalla
        val id = intent.getIntExtra("POKEMON_ID", -1)

        //escondemos el error al principio
        binding.tvErrorDetail.visibility = View.GONE

        if (id != -1) {
            // si el id llego bien pedimos los datos
            viewModel.fetchPokemonDetail(id)
            verCambios()
        } else {

            //Si falla el id avisamos con el siguiente mensaje de error
            binding.tvErrorDetail.visibility = View.VISIBLE
            binding.tvErrorDetail.text = "Error: No se ha podido cargar el pokemon"
        }

    }
    private fun verCambios() {
        //cuando lleguen los detalles del pokemon para enviarlos a pintar a la vista
        viewModel.pokemonDetail.observe(this) { p ->
            if (p != null) {
                rellenarDatos(p)
            }
        }

        //Condicional para mostrar o esconder el cargando
        viewModel.isLoading.observe(this) { cargando ->
            if (cargando) {
                binding.pbDetail.visibility = View.VISIBLE
                binding.layoutInfo.visibility = View.GONE
            } else {
                binding.pbDetail.visibility = View.GONE
                binding.layoutInfo.visibility = View.VISIBLE
            }

        }

        //Por si no hay internet, esta en modo avion, o falla algo
        viewModel.isError.observe(this) { error ->
            if (error) {
                binding.tvErrorDetail.visibility = View.VISIBLE
                binding.tvErrorDetail.text = "Error de red, comprueba tu conexion o desactiva el modo avion"
                binding.layoutInfo.visibility = View.GONE
            }
        }

    }

    private fun rellenarDatos(p: PokemonDetail) {
        // Ponemos el nombre y el id con la almohadilla
        binding.tvNameDetail.text = p.name
        binding.tvIdDetail.text = "#${p.id}"
        
        // Dividimos por 10 porque la API entrega datos raros (decimetros y hectogramos) y los convertimos
        // para mostar m y kg
        binding.tvHeight.text = "Altura: ${p.height / 10.0} m" 
        binding.tvWeight.text = "Peso: ${p.weight / 10.0} kg" 


        //Los tipos los separamos por comas con el joinToString
        val textoTipos = p.types.joinToString(", ") { it.type.name }
        binding.tvTypes.text = "Tipos: $textoTipos"

        // Usamos la libreria de Glide para que se puedan cargar la imagenes de los pokemon
        Glide.with(this)
            .load(p.sprites.frontDefault)
            .into(binding.ivPokemonDetail)
    }


}
