package com.example.idea_book.data

import com.example.idea_book.data.dto.request.IdeaReq
import com.example.idea_book.data.dto.response.IdeaRes
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface IdeaBookApi {
    @GET("ideas")
    suspend fun getIdeas(@Header("Authorization") token: String): List<IdeaRes>

    @POST("ideas")
    suspend fun createIdea(@Body idea: IdeaReq, @Header("Authorization") token: String): String

    @DELETE("ideas/{id}")
    suspend fun deleteIdea(@Path("id") id: Int, @Header("Authorization") token: String): String
}
