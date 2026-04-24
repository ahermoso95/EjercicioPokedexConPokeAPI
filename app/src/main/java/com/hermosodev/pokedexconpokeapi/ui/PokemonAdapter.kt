package com.hermosodev.pokedexconpokeapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hermosodev.pokedexconpokeapi.databinding.ItemPokemonBinding
import com.hermosodev.pokedexconpokeapi.model.PokemonBasicInfo

class PokemonAdapter(
    private var lista: List<PokemonBasicInfo>,
    private val click: (PokemonBasicInfo) -> Unit 
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {


    class PokemonViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val b = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return PokemonViewHolder(b)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val poke = lista[position]
        
        //Ponemos el nombre tal cual viene
        holder.binding.tvPokemonName.text = poke.name
        
        // el id con la almohadilla
        holder.binding.tvPokemonId.text = "#${poke.getPokemonId()}"

        // Cargamos la imagen con la libreria de Glide
        Glide.with(holder.itemView.context)
            .load(poke.getImageUrl())
            .into(holder.binding.ivPokemon)

        //al clicar en el pokemon avisamos para ir al detalle
        holder.itemView.setOnClickListener { click(poke)
        }

    }

    override fun getItemCount(): Int = lista.size

    // Para cuando cambiamos de pagina y hay que refrescar la lista
    fun actualizar(nuevaLista: List<PokemonBasicInfo>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}
