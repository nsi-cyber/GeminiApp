package com.papara.geminiapp.di

import com.papara.geminiapp.common.Constants
import com.papara.geminiapp.data.remote.ApiService
import com.papara.geminiapp.data.repository.ApiRepositoryImpl
import com.papara.geminiapp.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(api: ApiService): ApiRepository {
        return ApiRepositoryImpl(api)
    }






}