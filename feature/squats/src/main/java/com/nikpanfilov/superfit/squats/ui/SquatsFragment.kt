package com.nikpanfilov.superfit.squats.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.superfit.extensions.setOnBackPressedListener
import com.nikpanfilov.superfit.squats.databinding.FragmentSquatsBinding
import com.nikpanfilov.superfit.squats.presentation.SquatsEvent
import com.nikpanfilov.superfit.squats.presentation.SquatsState
import com.nikpanfilov.superfit.squats.presentation.SquatsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SquatsFragment : Fragment() {

	companion object {

		fun newInstance() = SquatsFragment()
	}

	private val binding by lazy { FragmentSquatsBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<SquatsViewModel>()

	private var sensorManager: SensorManager? = null
	private var accelerometerListener: AccelerometerListener? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
		val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
		accelerometerListener = AccelerometerListener(viewModel)
		sensorManager?.registerListener(
			accelerometerListener,
			accelerometer,
			SensorManager.SENSOR_DELAY_FASTEST
		)

		setOnBackPressedListener {
			sensorManager?.unregisterListener(accelerometerListener)
			viewModel.handle(SquatsEvent.NavigateBack)
		}
		viewModel.uiState.onEach {
			if (it is SquatsState.Content) {
				binding.countTextView.text = it.left.toString()
				if (it.left <= 0) {
					sensorManager?.unregisterListener(accelerometerListener)
					viewModel.handle(SquatsEvent.NavigateBack)
				}
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)
		binding.backButton.setOnClickListener { viewModel.handle(SquatsEvent.NavigateBack) }

		return binding.root
	}

	override fun onDetach() {
		sensorManager?.unregisterListener(accelerometerListener)
		super.onDetach()
	}

	//TODO("Добавить отсоединение акселерометра на уничтожение")
}