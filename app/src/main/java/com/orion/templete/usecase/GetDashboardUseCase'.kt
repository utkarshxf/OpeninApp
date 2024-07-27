package com.orion.templete.usecase

import android.util.Log
import com.orion.templete.data.model.opninAppDTO
import com.orion.templete.domain.repository.GetDashboardRepository
import com.orion.templete.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDashboardUseCase @Inject constructor(
    private val repository: GetDashboardRepository
){
    operator fun invoke():Flow<Resource<opninAppDTO>> = flow{
        emit(Resource.Loading(null))
        try {
            emit(Resource.Success(repository.getDashboardData()))
        }catch (e:Exception){
            emit(Resource.Error(e.message))
        }
    }
}