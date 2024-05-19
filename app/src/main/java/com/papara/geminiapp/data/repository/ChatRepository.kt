package com.papara.geminiapp.data.repository

import com.papara.geminiapp.data.api.ApiService
import com.papara.geminiapp.data.model.ChatRequest
import com.papara.geminiapp.data.model.ChatResponse

class ChatRepository(private val apiService: ApiService) {
    suspend fun sendMessage(message: String): ChatResponse {
        val request = ChatRequest(message)
        return apiService.sendMessage(request)
    }
}