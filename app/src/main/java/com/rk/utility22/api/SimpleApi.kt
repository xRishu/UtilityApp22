package com.rk.utility22.api

import com.rk.utility22.Model.Member
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    @GET("/search/{country}")
    suspend fun getMember(
        @Query("country") country: String
    ):Response<List<Member>>
}
// http://universities.hipolabs.com/search?country=India