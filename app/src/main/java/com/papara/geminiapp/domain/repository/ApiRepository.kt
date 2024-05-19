package com.papara.geminiapp.domain.repository

import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.response.MessageResponse
import retrofit2.http.Body
import retrofit2.http.Query

interface ApiRepository {

    suspend fun sendMessage( apiKey:String?, body: MessageRequestBody): MessageResponse?

}