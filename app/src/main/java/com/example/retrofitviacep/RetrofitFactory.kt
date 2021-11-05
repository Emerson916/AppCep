package com.example.retrofitviacep

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import retrofit2.create
import java.net.URL

class RetrofitFactory {

    //val URL = "https://viacep.com.br/ws/"
    val URL = "http://localhost:8080/api/clientes/"

    val retrofitFactory =
            Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun retrofitService() : RetrofitService{
        return retrofitFactory.create(RetrofitService::class.java)
    }

}



