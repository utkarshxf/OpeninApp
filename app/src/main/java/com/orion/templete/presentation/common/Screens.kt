package com.orion.templete.presentation.common

sealed class Screens(val route: String){
    object Home : Screens("home_route")
    object Search : Screens("search_route")
    object Campaigns : Screens("campaigns_route")
    object Profile : Screens("profile_route")
}
