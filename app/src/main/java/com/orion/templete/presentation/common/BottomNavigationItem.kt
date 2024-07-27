package com.orion.templete.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.orion.templete.R

data class BottomNavigationItem(
    val label : String = "",
    val icon : Int = R.drawable.link,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Links",
                icon = R.drawable.link,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "Courses",
                icon = R.drawable.magazine,
                route = Screens.Search.route
            ),
            BottomNavigationItem(
                label = "Campaigns",
                icon = R.drawable.fastforward,
                route = Screens.Profile.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = R.drawable.user,
                route = Screens.Profile.route
            )
        )
    }
}