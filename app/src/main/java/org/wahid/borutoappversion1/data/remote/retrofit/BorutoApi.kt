package org.wahid.borutoappversion1.data.remote.retrofit

import org.wahid.borutoappversion1.domain.model.ApiResponse
import org.wahid.borutoappversion1.utils.Constants.HEROES_END_POINT
import org.wahid.borutoappversion1.utils.Constants.HEROES_SEARCH_END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET(HEROES_END_POINT)
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1,
    ): ApiResponse

    @GET(HEROES_SEARCH_END_POINT)
    suspend fun searchHeroes(
        @Query("name") name: String,
    ): ApiResponse


}