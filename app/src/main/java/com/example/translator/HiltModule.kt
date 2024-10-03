package com.example.translator

import com.example.translator.api.Api
import com.example.translator.api.ApiRepo
import com.example.translator.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideRepo(api: Api) :ApiRepo= ApiRepo(api)
}