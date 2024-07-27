package com.orion.templete.presentation.dashboard.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.orion.templete.R
import com.orion.templete.data.model.Data
import com.orion.templete.data.model.Link
import com.orion.templete.presentation.ui.theme.ColorSurfaceLight
import com.orion.templete.presentation.ui.theme.PrimaryBackground
import com.orion.templete.util.dashedBorder
import com.orion.templete.util.mediumPaddingValue
import com.orion.templete.util.smallPaddingValue

@Composable
fun CustomTabBar(data: Data) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Top Links", "Recent Links")

    Column(verticalArrangement = Arrangement.spacedBy(mediumPaddingValue)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                tabs.forEachIndexed { index, title ->
                    val bgColor = if (selectedTabIndex == index) PrimaryBackground else ColorSurfaceLight
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .background(bgColor)
                            .clickable { selectedTabIndex = index }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        text = title,
                        color = if (selectedTabIndex == index) Color.White else Color.Gray
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                modifier = Modifier.size(36.dp)
            )
        }

        when (selectedTabIndex) {
            0 -> TopLinksTab(data.top_links)
            1 -> RecentLinksTab(data.recent_links)
        }
    }
}

@Composable
fun RecentLinksTab(recentLinks: List<Link>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(smallPaddingValue)
    ) {
        recentLinks.forEach { link ->
            LinkItem(link)
        }
    }
}

@Composable
fun TopLinksTab(topLinks: List<Link>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(smallPaddingValue)
    ) {
        topLinks.forEach { link ->
            LinkItem(link)
        }
    }
}

@Composable
fun LinkItem(link: Link) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(vertical = 8.dp)
    ) {
        val clipboardManager = LocalClipboardManager.current
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = link.original_image,
                    contentScale = ContentScale.Inside
                ),
                contentDescription = "App Logo",
                modifier = Modifier.size(52.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = truncateTextAfterChars(link.title, 20),
                        maxLines = 1,
                        fontSize = 20.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = link.total_clicks.toString(),
                        fontSize = 20.sp,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = link.created_at,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Clicks",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .dashedBorder(MaterialTheme.colorScheme.primary, 4.dp, 2.dp, 2.dp)
                .background(Color(0xFFE6F3FF))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = link.smart_link,
                color = Color.Blue,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.copy),
                contentDescription = "Copy",
                tint = Color.Blue,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        clipboardManager.setText(AnnotatedString(link.smart_link))
                        Toast
                            .makeText(context, "Copied to Clip Board", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }
    }
}

fun truncateTextAfterChars(text: String, charLimit: Int): String {
    if (text.length <= charLimit) return text
    for (i in charLimit downTo 0) {
        if (text[i] == ' ') return text.substring(0, i) + "..."
    }
    return text.substring(0, charLimit) + "..."
}

@Preview(showBackground = true)
@Composable
fun TabScreenPreview() {
//    CustomTabBar(data.data)
}