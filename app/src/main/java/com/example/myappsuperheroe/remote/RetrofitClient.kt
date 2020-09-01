package com.example.myappsuperheroe.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private const val BASE_URL = "https://akabab.github.io/superhero-api/api/"
        fun retrofitInstance(): ApiSH {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiSH::class.java)
        }
    }
}