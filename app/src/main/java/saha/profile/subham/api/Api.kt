package saha.profile.subham.api

import retrofit2.http.POST
import retrofit2.http.Query
import saha.profile.subham.model.ApplicationData

interface Api {
    @POST("list")
    suspend fun getApplicationData(@Query("kid_id") query : String) : ApplicationData
}