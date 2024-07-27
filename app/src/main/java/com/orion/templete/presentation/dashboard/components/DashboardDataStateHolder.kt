package com.orion.templete.presentation.dashboard.components

import com.orion.templete.data.model.opninAppDTO

data class DashboardDataStateHolder (
    val isLoading: Boolean = false,
    val data: opninAppDTO? = null,
    val error: String = ""
)