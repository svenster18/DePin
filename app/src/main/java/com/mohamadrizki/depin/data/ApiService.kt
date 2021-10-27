package com.mohamadrizki.depin.data

import com.mohamadrizki.depin.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("pengguna")
    fun getUser() : Call<UserResponse>
}