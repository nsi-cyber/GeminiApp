package com.papara.geminiapp.data.api

import com.papara.geminiapp.data.model.ChatRequest
import com.papara.geminiapp.data.model.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("chat")
    suspend fun sendMessage(@Body request: ChatRequest): ChatResponse

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }
}