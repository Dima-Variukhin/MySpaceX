package com.example.myspacex.data.cloud

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchesService {
    @GET("launches")
    fun getLaunchesAsync(@Query("launch_year") year: String): Deferred<Response<List<LaunchesCloud>>>
}