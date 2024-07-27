package com.orion.templete.domain.repository

import com.orion.templete.data.model.opninAppDTO

interface GetDashboardRepository {
    suspend fun getDashboardData(): opninAppDTO
}