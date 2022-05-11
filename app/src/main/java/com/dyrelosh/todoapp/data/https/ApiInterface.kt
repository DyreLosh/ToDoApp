package com.dyrelosh.todoapp.data.https

import com.dyrelosh.todoapp.data.model.NewTask
import com.dyrelosh.todoapp.data.model.TaskResponse
import com.dyrelosh.todoapp.data.model.Token
import com.dyrelosh.todoapp.data.model.UserCreate
import com.dyrelosh.todoapp.data.model.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiInterface {

    @POST("user/create")
    fun userCreate(@Body userCreate: UserCreate): Call<Token>

    @POST("user/login")
    fun userLogin(@Body userLogin: UserLogin): Call<Token>

    @GET("todos")
    fun getTasks(
        @Header("Authorization") token: String
    ): Call<List<TaskResponse>>
    @POST("todos")
    fun addNewTask(
        @Header("Authorization") token: String,
        @Body newTask: NewTask
    ) : Call<Unit>

    @PUT("todos/mark")
    fun markTodo(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ) : Call<Unit>
}