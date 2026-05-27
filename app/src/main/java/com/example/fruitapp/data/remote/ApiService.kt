package com.example.fruitapp.data.remote

import com.example.fruitapp.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("api/users")
    suspend fun getUsers(): List<User>
}