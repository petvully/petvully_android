package org.e1i4.petvully.data.remote

import org.e1i4.petvully.data.remote.model.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PVService {

    @GET("login/")
    suspend fun login(
        @Query("userId") id: String,
        @Query("pw") pw: String,
    ): APIResponse<String>

    @GET("signup/")
    suspend fun signup(
        @Query("userId") id: String,
        @Query("pw") pw: String,
        @Query("token") token: String
    ): APIResponse<String>
}