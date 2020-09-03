package com.example.myappsuperheroe.remote

import com.example.myappsuperheroe.pojo.Images
import com.example.myappsuperheroe.pojo.SuperHero
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiSH {

    @GET("all.json")
    fun getPhotos(): Call<List<SuperHero>>

    @GET("/all.json{images}")
    fun getImages(): Call<List<Images>>

    @GET("/all.json{images{xs}}")
    fun getSHbyI(@Path("id") id: String): Call<SuperHero>
}