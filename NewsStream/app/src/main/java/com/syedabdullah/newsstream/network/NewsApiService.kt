package com.syedabdullah.newsstream.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.syedabdullah.newsstream.model.News
import com.syedabdullah.newsstream.network.ApiConstant.Companion.BASE_URL
import com.syedabdullah.newsstream.network.ApiConstant.Companion.KEY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService{
    @GET("/v2/top-headlines?country=us&apiKey=$KEY")
    suspend fun getTopHeadlineNews():News
}

object NewsApi{
    val retrofitService:NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}