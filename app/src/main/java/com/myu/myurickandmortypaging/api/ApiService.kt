package com.myu.myurickandmortypaging.api

import com.myu.myurickandmortypaging.model.CharacterResponse
import com.myu.myurickandmortypaging.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getAllCharacters(
        @Query("page") page : Int
    ) : Response<CharacterResponse>
}