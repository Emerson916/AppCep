package com.example.retrofitviacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_cliente : AppCompatActivity() {

    lateinit var  ed_1 : EditText
    lateinit var  ed_2 : EditText
    lateinit var  ed_3 : EditText
    lateinit var  btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)

        ed_1 = findViewById(R.id.ed_1)
        ed_2 = findViewById(R.id.ed_2)
        ed_3 = findViewById(R.id.ed_3)

        btn.setOnClickListener{

            val cliente = Cliente(
                    0,
                    ed_1.text.toString(),
                    ed_2.text.toString(),
                    ed_3.text.toString()
            )

            //obter uma instância da conexão com o Backend
            val remote = RetrofitFactory().retrofitService()

            val call: Call<Cliente> = remote.gravarClientes(cliente)

            call.enqueue(object : Callback<Cliente> {
                override fun onResponse(call: Call<Cliente>, response: Response<Cliente>) {
                    Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()


                }

                override fun onFailure(call: Call<Cliente>, t: Throwable) {
                   // Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

                    Log.i("cep", t.message.toString())
                }

            })
        }


    }
}