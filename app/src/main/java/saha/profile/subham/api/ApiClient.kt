package saha.profile.subham.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{
        val BASE_URL = "https://navkiraninfotech.com/g-mee-api/api/v1/apps/"

        fun getInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}