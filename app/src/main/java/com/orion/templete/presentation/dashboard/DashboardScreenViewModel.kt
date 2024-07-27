package com.orion.templete.presentation.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.templete.presentation.dashboard.components.DashboardDataStateHolder
import com.orion.templete.usecase.GetDashboardUseCase
import com.orion.templete.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    private val getDashboardUseCase: GetDashboardUseCase
) :ViewModel(){
    val dashboardData = mutableStateOf(DashboardDataStateHolder())

    init {
        getDashboardData()
    }
    private fun getDashboardData() {
        getDashboardUseCase().onEach {
            when(it){
                is Resource.Loading -> {
                    dashboardData.value = DashboardDataStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    dashboardData.value = DashboardDataStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    dashboardData.value = DashboardDataStateHolder(error = it.message.toString())
                }

            }
        }.launchIn(viewModelScope)
    }

}