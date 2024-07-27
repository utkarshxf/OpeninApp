package com.orion.templete.data.repository

import android.util.Log
import com.orion.templete.data.model.opninAppDTO
import com.orion.templete.data.network.ApiService
import com.orion.templete.domain.repository.GetDashboardRepository
import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject

class GetDashboardImplementation @Inject constructor(private val apiService: ApiService): GetDashboardRepository, SafeApiRequest(){
    override suspend fun getDashboardData(): opninAppDTO {
        val response =safeApiRequest { apiService.getDashboardData() }
        return response
    }

}