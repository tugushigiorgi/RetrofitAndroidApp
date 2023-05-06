package com.example.retrofitapp.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitApi {

    @GET("posts")
    fun postlist( ): Call<List<PosDto>>


    @GET("posts/{id}")
    fun getpost(@Path("id") id :String ):Call<PosDto>





}