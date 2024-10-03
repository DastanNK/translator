package com.example.translator.api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/"

    fun getClient(): Retrofit {
        Log.d("", "hi")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:Api = getClient().create(Api::class.java)
}