package com.example.retrofitviacep

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CepsAdapter(var context: Context) : RecyclerView.Adapter<CepsAdapter.CepViewHolder>() {

    private var listaCeps = emptyList<Cep>()

    fun updateListasCeps(lista : List<Cep>){
        listaCeps = lista

        //Notifica que teve aterações e modifica a tela
        notifyDataSetChanged()
    }

    //Criando a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CepViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ceps_recycler_view_layout, parent, false)

        return CepViewHolder(view)
    }

    //Constroi a holder na tela e manda a posição que for criar
    override fun onBindViewHolder(holder: CepViewHolder, position: Int) {

        Log.i("xptoholder", "onBindViewHolder" )

        val cep = listaCeps[position]

        holder.tvCep.text = cep.cep
        holder.tvLogradouro.text = cep.logradouro
        holder.tvLocalidade.text = cep.cidade
    }

    //Pega a contagem de itens da tela
    override fun getItemCount(): Int {

        Log.i("xptoholder", "getItemCount" )

        return listaCeps.size
    }


    //Cria a Holder com os elementos passados
    class CepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvCep = itemView.findViewById<TextView>(R.id.tv_cep)
        val tvLogradouro = itemView.findViewById<TextView>(R.id.tv_logradouro)
        val tvLocalidade = itemView.findViewById<TextView>(R.id.tv_localidade)




    }




}