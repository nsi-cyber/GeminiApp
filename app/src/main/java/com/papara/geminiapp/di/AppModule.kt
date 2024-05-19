package com.papara.geminiapp.di

import com.papara.geminiapp.data.api.ApiService
import com.papara.geminiapp.data.api.RetrofitInstance
import com.papara.geminiapp.data.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideChatRepository(apiService: ApiService): ChatRepository {
        return ChatRepository(apiService)
    }
}