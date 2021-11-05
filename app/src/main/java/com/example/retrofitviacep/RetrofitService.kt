package com.example.retrofitviacep


import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    // Método que será responsável por chamr a API

    //https://viacep.com.br/ws/06600025/json/

    @GET("{CEP}/json/")
    fun getCEP(@Path("CEP") cep: String): Call<Cep>


    @GET("{uf}/{cidade}/{logradouro}/json")
    fun getCEPByLogradouro(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("logradouro") logradouro: String
    ): Call<List<Cep>>

    //@POST()
    @POST("/ceps")
    fun gravarCep(@Body cep: Cep): Call<Cep>

    @DELETE("/ceps/{id}")
    fun excluir(@Path("id") id: Int) {
    }


    /*Fazendo um grup para o cliente*/

    @POST("clientes")
    fun gravarClientes(@Body cliente: Cliente): Call<Cliente>

}
