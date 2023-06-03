package com.nikpanfilov.superfit.statistics.ui

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.nikpanfilov.superfit.theme.R

internal fun BarChart.setupBarChart() {
	this.apply {
		description.isEnabled = false
		setDrawGridBackground(false)
		setTouchEnabled(false)
		isDragEnabled = false
		setScaleEnabled(false)
		setPinchZoom(false)
		setDrawBorders(true)
		setBorderWidth(1f)
		setBorderColor(Color.WHITE)
		legend.isEnabled = false

		xAxis.position = XAxis.XAxisPosition.BOTTOM
		xAxis.setDrawGridLines(true)
		xAxis.textColor = Color.WHITE
		xAxis.labelCount = 5

		axisLeft.setDrawGridLines(true)
		axisLeft.textColor = Color.WHITE
		axisLeft.labelCount = 2

		axisRight.isEnabled = false
	}
}

internal fun LineChart.setupLineChart() {
	this.apply {
		description.isEnabled = false
		setDrawGridBackground(false)
		setTouchEnabled(false)
		isDragEnabled = false
		setScaleEnabled(false)
		setPinchZoom(false)
		setDrawBorders(true)
		setBorderWidth(1f)
		setBorderColor(Color.WHITE)
		legend.isEnabled = false

		xAxis.position = XAxis.XAxisPosition.BOTTOM
		xAxis.setDrawGridLines(true)
		xAxis.textColor = Color.WHITE
		xAxis.labelCount = 3

		axisLeft.setDrawGridLines(true)
		axisLeft.textColor = Color.WHITE
		axisLeft.labelCount = 2

		axisRight.isEnabled = false
	}
}

internal fun LineChart.setData(xValues: List<String>, yValues: List<Int>) {
	val entries = ArrayList<Entry>()
	for (i in yValues.indices) {
		entries.add(Entry(i.toFloat(), yValues[i].toFloat()))
	}

	val purple = ContextCompat.getColor(this.context, R.color.purple)
	this.data = LineData(generateLineDataSet(entries, purple))

	this.xAxis.valueFormatter = CustomXAxisValueFormatter(xValues)

	this.invalidate()
}

internal fun BarChart.setData(xValues: List<String>, yValues: List<Int>) {
	this.xAxis.valueFormatter = CustomXAxisValueFormatter(xValues)

	val entries = ArrayList<BarEntry>()
	for (i in yValues.indices) {
		entries.add(BarEntry(i.toFloat(), yValues[i].toFloat()))
	}

	val purple = ContextCompat.getColor(this.context, R.color.purple)
	this.data = BarData(generateBarDataSet(entries, purple))

	this.invalidate()
}

private fun generateLineDataSet(entries: List<Entry>, color: Int): ILineDataSet {
	val dataSet = LineDataSet(entries, "Data Set")
	dataSet.color = color
	dataSet.setCircleColor(color)
	dataSet.lineWidth = 2f
	dataSet.circleRadius = 4f
	dataSet.setDrawCircleHole(false)
	dataSet.setDrawValues(false)
	dataSet.setDrawFilled(false)
	dataSet.setDrawHighlightIndicators(false)
	return dataSet
}

private fun generateBarDataSet(entries: List<BarEntry>, color: Int): IBarDataSet {
	val dataSet = BarDataSet(entries, "Data Set")
	dataSet.color = color
	dataSet.setDrawValues(false)
	return dataSet
}

class CustomXAxisValueFormatter(private val labels: List<String>) : ValueFormatter() {

	override fun getAxisLabel(value: Float, axis: AxisBase?): String {
		return if (value.toInt() >= 0 && value.toInt() < labels.size) {
			labels[value.toInt()]
		} else {
			""
		}
	}
}