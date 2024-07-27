package com.orion.templete.data.network


import com.orion.templete.data.model.opninAppDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/dashboardNew")
    suspend fun getDashboardData(): retrofit2.Response<opninAppDTO>
    companion object{
        const val BASE_URL = "https://api.inopenapp.com/"
        const val BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
    }
}