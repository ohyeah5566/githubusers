package com.example.githubusers.api

import com.example.githubusers.data.GithubUser
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface GithubUserService {
    @GET("users")
    suspend fun getUsers(): List<GithubUser>
}

fun getGithubUserService(): GithubUserService {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(GithubUserService::class.java)
}