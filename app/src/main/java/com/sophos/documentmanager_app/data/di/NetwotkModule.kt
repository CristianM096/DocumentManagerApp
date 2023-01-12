package com.sophos.documentmanager_app.data.di

import dagger.Module
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn
class NetwotkModule {
    companion object{
        fun getRestEngine(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://6w33tkx4f9.execute-api.us-east-1.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}