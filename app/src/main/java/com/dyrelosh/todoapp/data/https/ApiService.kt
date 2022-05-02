package com.dyrelosh.todoapp.data.https

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiService {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://megarick-todo-app.herokuapp.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)
}