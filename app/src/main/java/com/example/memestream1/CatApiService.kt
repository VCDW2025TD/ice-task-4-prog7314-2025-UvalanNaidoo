package com.example.memestream1

import retrofit2.http.GET
import retrofit2.http.Header

interface CatApiService {
    @GET("images/search")
    suspend fun getRandomCat(
        @Header("x-api-key") apiKey: String = ApiConstants.API_KEY
    ): List<CatImage>
}
