package com.example.retrofitviacep

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    // Método que será responsável por chamr a API

    //https://viacep.com.br/ws/06600025/json/

    @GET("{CEP}/json/")
    fun getCEP(@Path("CEP") cep: String) : Call<Cep>


    
    //@POST()

}