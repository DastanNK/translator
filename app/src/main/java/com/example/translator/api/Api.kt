package com.example.translator.api

import com.example.translator.TranslatorData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("en/{word}")
    fun getApi(@Path("word") word: String): Call<TranslatorData>
}