package com.rk.utility22.repository

import com.rk.utility22.Model.Member
import com.rk.utility22.api.RetroFitInstance
import retrofit2.Response

class repository {
    suspend fun getMember(): Response<List<Member>> {
        return RetroFitInstance.api.getMember("India")
    }
}