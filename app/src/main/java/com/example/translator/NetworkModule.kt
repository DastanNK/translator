package com.example.translator

import com.example.translator.api.Api
import com.example.translator.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApi(): Api {
        return RetrofitClient.api
    }
}