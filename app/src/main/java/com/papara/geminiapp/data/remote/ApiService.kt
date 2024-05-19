package com.papara.geminiapp.data.remote

import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.response.MessageResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Streaming


interface ApiService {

        @POST("./gemini-pro:generateContent")
        suspend fun sendMessage(@Query("key") apiKey:String?,@Body body:MessageRequestBody) : MessageResponse?


    }
