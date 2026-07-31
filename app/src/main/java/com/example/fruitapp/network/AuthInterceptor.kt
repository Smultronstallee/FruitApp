package com.example.fruitapp.network

import com.example.fruitapp.data.local.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private  val tokenMan: TokenManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenMan.getToken()
        }
        val request = chain.request().newBuilder()
        if(!token.isNullOrEmpty()){
            request.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }
}