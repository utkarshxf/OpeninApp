package com.orion.templete.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.data.model.opninAppDTO
import com.orion.templete.presentation.dashboard.components.CustomTabBar
import com.orion.templete.presentation.dashboard.components.LineChartScreen
import com.orion.templete.presentation.dashboard.components.MyLineChart
import com.orion.templete.presentation.ui.theme.BlueBorder
import com.orion.templete.presentation.ui.theme.TempleteTheme
import com.orion.templete.presentation.ui.theme.WhatsappGreen
import com.orion.templete.util.largePaddingValue
import com.orion.templete.util.mediumPaddingValue
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardScreenViewModel = hiltViewModel()) {
    val stateOfDashboard = viewModel.dashboardData.value
    when {
        stateOfDashboard.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        stateOfDashboard.error.isNotBlank() -> {
            Text(text = stateOfDashboard.error)
        }
        stateOfDashboard.data != null -> {
            val data = stateOfDashboard.data
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(color = MaterialTheme.colorScheme.primary)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DashBoard()
                GreetingCard(data = data)
            }
        }
    }
}

@Composable
fun GreetingCard(data: opninAppDTO) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(mediumPaddingValue))
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(mediumPaddingValue),
        verticalArrangement = Arrangement.spacedBy(largePaddingValue),
    ) {
        GreetingText()
        LineChart(data.data.overall_url_chart) // This can be updated if you need specific data
        VerticalRowItem(data = data)
        ViewButton("View Analytics", R.drawable.arrow) {}
        CustomTabBar(data.data)
        ViewButton("View All Links", R.drawable.links) {}
        ContactUs()
    }
}

@Composable
fun VerticalRowItem(data: opninAppDTO) {
    val itemsList = listOf(
        Pair("Daily Clicks", R.drawable.daleyclicks),
        Pair("Top Location", R.drawable.toploacation),
        Pair("Top Source", R.drawable.topsource)
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(itemsList) { item ->
            BoxCard(title = item.first, iconResId = item.second, value = when (item.first) {
                "Daily Clicks" -> data.today_clicks.toString()
                "Top Location" -> data.top_location
                "Top Source" -> data.top_source
                else -> "N/A"
            })
        }
    }
}

@Composable
fun BoxCard(title: String, iconResId: Int, value: String) {
    Column(
        modifier = Modifier
            .size(150.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Image(
            painterResource(id = iconResId),
            contentDescription = "Setting Button",
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = value, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = title, fontSize = 16.sp, color = Color.DarkGray.copy(0.6f))
    }
}

@Composable
fun LineChart(ChartData: Map<String, Float>) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(220.dp)
        .padding(top = 16.dp)
        .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        Column {
            OverViewTime()
            MyLineChart(ChartData)
        }
    }
}

@Composable
fun OverViewTime() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 0.dp)
    ) {
        Text(
            text = "Overview", fontSize = 14.sp, color = androidx.compose.ui.graphics.Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(4.dp)
                .border(1.dp, androidx.compose.ui.graphics.Color.Gray, RoundedCornerShape(8.dp))
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(text = "22 Aug - 23 Sept", fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.Black)
            Spacer(modifier = Modifier.size(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.time), contentDescription = null, tint = androidx.compose.ui.graphics.Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ContactUs() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WhatsappGreen.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = WhatsappGreen.copy(alpha = 0.7f),
                    RoundedCornerShape(8.dp)
                )
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = R.drawable.whatsapp),
                contentDescription = "Link",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Talk to us", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = BlueBorder.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = BlueBorder, RoundedCornerShape(8.dp))
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = R.drawable.questionmark),
                contentDescription = "question mark",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Frequently Asked Questions", fontSize = 20.sp)
        }
    }
}

@Composable
fun ViewButton(name: String, icon: Int, click: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(4.dp)
            .border(width = 2.dp, color = Color.LightGray, RoundedCornerShape(8.dp)).clickable { click() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(38.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun GreetingText() {
    val currentTime = LocalTime.now()
    val greeting = when (currentTime.hour) {
        in 12..16 -> "Good Afternoon"
        in 17..20 -> "Good Evening"
        in 0..11 -> "Good Morning"
        else -> "Good Night"
    }
    Column(
        modifier = Modifier.padding(top = 32.dp)
    ) {
        Text(text = greeting, fontSize = 18.sp, color = Color.Gray)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Ajay Manav ", fontSize = 32.sp)
            Image(
                painterResource(id = R.drawable.imagewave),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun DashBoard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Dashboard",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            painter = painterResource(id = R.drawable.frame_7),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun ActionRow(iconResId: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, color = MaterialTheme.colorScheme.background, RoundedCornerShape(6.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.padding(12.dp)
        )
        Text(text = text, modifier = Modifier.padding(12.dp))
    }
}

@Preview
@Composable
private fun MyScreen() {
    TempleteTheme {
        DashboardScreen()
    }
}
