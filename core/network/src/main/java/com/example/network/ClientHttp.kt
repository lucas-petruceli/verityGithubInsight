package com.example.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getClientHttp(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}