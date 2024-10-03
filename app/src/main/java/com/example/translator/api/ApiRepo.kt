package com.example.translator.api

import android.util.Log
import com.example.translator.TranslatorData
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class ApiRepo(private val api: Api) {
    var translatorData = MutableStateFlow<NetworkResult<TranslatorData>>(NetworkResult.Initial())
    fun onTranslate(word:String){
        Log.d("ApiRepo", "onTranslate called with word: $word")
        translatorData.value=NetworkResult.Loading()
        api.getApi(word).enqueue(object : Callback<TranslatorData> {
            override fun onFailure(call: Call<TranslatorData>, t: Throwable) {
                t.localizedMessage.let {
                    translatorData.value = NetworkResult.Error(it)
                }
                Log.d("ApiRepo", "Error: ${t.localizedMessage}")
                t.printStackTrace()
            }
            override fun onResponse(call: Call<TranslatorData>, response: Response<TranslatorData>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        translatorData.value=NetworkResult.Success(it)
                    }
                }else{
                    response.errorBody()?.let {
                        translatorData.value=NetworkResult.Error(message = it.toString())
                    }
                }
                Log.d("ApiRepo", "Response: ${response.body()}")
            }
        })

    }
}