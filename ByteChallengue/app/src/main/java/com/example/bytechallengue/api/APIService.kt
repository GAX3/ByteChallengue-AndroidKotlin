package com.example.bytechallengue.api

import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("eeced007-6b29-4c9d-ab63-c115a990d927")
    fun getData(): Call<List<MyDataItem>>
}