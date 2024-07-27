package com.orion.templete.presentation.dashboard.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.LineType
import com.orion.templete.presentation.ui.theme.PrimaryBackground

@Composable
fun MyLineChart(data: Map<String, Float>) {

    val pointsData: List<Point> =listOf(Point(0f, 0f)) +  data.entries.mapIndexed { index, entry ->
        Point(index.toFloat()+1, entry.value)
    }

    val xLabels = listOf(" ","Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    val xAxisData = AxisData.Builder()
        .axisStepSize(50.dp)
        .backgroundColor(Color.White)
        .steps(pointsData.size - 1)
        .labelData { i -> xLabels.getOrNull(i) ?: "" }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val maxYValue = data.values.maxOrNull() ?: 0f
    val steps = 4
    val yScale = (maxYValue / steps).toInt()

    // Configure Y-axis data
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.White)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val value = (i * yScale).toString()
            value
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(color = PrimaryBackground , lineType = LineType.Straight()),
                    IntersectionPoint(color = Color.Transparent),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(color = PrimaryBackground),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )

    LineChart(
        modifier = Modifier
            .fillMaxSize()
            .height(300.dp),
        lineChartData = lineChartData
    )
}

@Composable
fun LineChartScreen() {
    // Example API data for testing
    val overallUrlChart = mapOf(
        "00:00" to 0.0f,
        "01:00" to 10.0f,
        "02:00" to 0.0f,
        "03:00" to 30.0f,
        "04:00" to 0.0f,
        "05:00" to 3.0f,
        "06:00" to 60.0f,
        "07:00" to 40.0f,
        "08:00" to 0.0f,
        "09:00" to 0.0f,
        "10:00" to 0.0f,
        "11:00" to 0.0f,
        "12:00" to 2.0f,
        "13:00" to 0.0f,
        "14:00" to 0.0f,
        "15:00" to 0.0f,
        "16:00" to 0.0f,
        "17:00" to 0.0f,
        "18:00" to 0.0f,
        "19:00" to 0.0f,
        "20:00" to 0.0f,
        "21:00" to 0.0f,
        "22:00" to 0.0f,
        "23:00" to 0.0f,
    )

    MyLineChart(data = overallUrlChart)
}
