package com.example.retrofitviacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var tvEndereco: TextView
    lateinit var editTextCep: EditText

    lateinit var editTextRua: EditText
    lateinit var editTextCidade: EditText
    lateinit var editTextEstado: EditText
    lateinit var textViewEndereco: TextView
    lateinit var buttonEndereco: Button

    lateinit var rvCeps: RecyclerView
    lateinit var cepsAdapter: CepsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        tvEndereco = findViewById(R.id.tvEndereco)
        editTextCep = findViewById(R.id.editTextCep)

        editTextRua = findViewById(R.id.editTextRua)
        editTextCidade = findViewById(R.id.editTextCidade)
        editTextEstado = findViewById(R.id.editTextEstado)

        buttonEndereco = findViewById(R.id.buttonEndereco)


        // Configuração da RecyclerView
        // * Inicialização da RV(RecycleView) e do Adapter
        rvCeps = findViewById(R.id.rv_ceps)
        cepsAdapter = CepsAdapter(this)

        // * Determinar o layout da RV(RecycleView)
        //rvCeps.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvCeps.layoutManager = GridLayoutManager(this, 2 )


        //* Definindo a Adapter da RV(RecycleView)
        rvCeps.adapter = cepsAdapter

        button.setOnClickListener{
            //obter uma instância da conexão com o Backend
            val remote = RetrofitFactory().retrofitService()

            //Criar uma chamada para o endpoint /cep/json
            val call: Call<Cep> = remote.getCEP(editTextCep.text.toString())

            //Executar a chamada paraa a API
            call.enqueue(object : Callback<Cep>{
                override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                    val cep = response.body()
                    tvEndereco.text = cep.toString()
                }

                override fun onFailure(call: Call<Cep>, t: Throwable) {
                    Log.i("cep", t.message.toString())
                }

            })
        }

        buttonEndereco.setOnClickListener{
            //obter uma instância da conexão com o Backend
            val remote = RetrofitFactory().retrofitService()

            //Criar uma chamada para o endpoint /cep/json

            val call: Call<List<Cep>> = remote.getCEPByLogradouro(editTextEstado.text.toString(), editTextCidade.text.toString(), editTextRua.text.toString())

            //Executar a chamada paraa a API
            call.enqueue(object : Callback<List<Cep>>{
                override fun onResponse(call: Call<List<Cep>>, response: Response<List<Cep>>) {
                    val ceps = response.body()

                    cepsAdapter.updateListasCeps(ceps!!)

                }

                override fun onFailure(call: Call<List<Cep>>, t: Throwable) {
                    Log.i("cep", t.message.toString())
                }
            })
        }
    }
}


