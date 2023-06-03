package com.nikpanfilov.superfit.statistics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.superfit.statistics.databinding.FragmentStatisticsBinding
import com.nikpanfilov.superfit.statistics.presentation.StatisticsEvent
import com.nikpanfilov.superfit.statistics.presentation.StatisticsState
import com.nikpanfilov.superfit.statistics.presentation.StatisticsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticsFragment : Fragment() {
	companion object {

		fun newInstance() = StatisticsFragment()
	}

	private val binding by lazy { FragmentStatisticsBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<StatisticsViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.backButton.setOnClickListener { viewModel.handle(StatisticsEvent.NavigateBack) }

//		val xValues = listOf("01.01.2019", "25.06.2019", "01.01.2020")
//		val yValues = listOf(10, 20, 15)
		binding.weightChart.setupLineChart()
		binding.trainingChart.setupBarChart()
		viewModel.uiState.onEach {
			if (it is StatisticsState.Content) {
				binding.weightChart.setData(xValues = it.body.map { it.date }, yValues = it.body.map { it.weight })
				setBarChart(binding.chipGroup.checkedChipId)
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)

		binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
			setBarChart(checkedIds.first())
		}


		return binding.root
	}

	private fun setBarChart(chipId: Int) {
		val type = when (chipId) {
			binding.crunchChip.id  -> TrainingType.CRUNCH
			binding.plankChip.id   -> TrainingType.PLANK
			binding.pushupsChip.id -> TrainingType.PUSH_UP
			binding.squatsChip.id  -> TrainingType.SQUATS
			else                   -> TrainingType.RUNNING
		}
		val state = viewModel.uiState.value as? StatisticsState.Content ?: return
		val trainings = state.trainings.filter { it.exercise == type }
		binding.trainingChart.setData(
			xValues = trainings.map { it.date }.sorted(),
			yValues = trainings.map { it.repeatCount }.sorted()
		)
	}
}