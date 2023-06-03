package com.nikpanfilov.superfit.plank.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.superfit.extensions.setOnBackPressedListener
import com.nikpanfilov.superfit.plank.R
import com.nikpanfilov.superfit.plank.databinding.DialogBinding
import com.nikpanfilov.superfit.plank.databinding.FragmentPlankBinding
import com.nikpanfilov.superfit.plank.presentation.PlankEvent
import com.nikpanfilov.superfit.plank.presentation.PlankState
import com.nikpanfilov.superfit.plank.presentation.PlankViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlankFragment : Fragment() {

	companion object {

		private const val SECOND = 1000L
		fun newInstance() = PlankFragment()
	}

	private val binding by lazy { FragmentPlankBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<PlankViewModel>()

	private var timer: CountDownTimer? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		setOnBackPressedListener { viewModel.handle(PlankEvent.Finish) }
		binding.finishButton.setOnClickListener { viewModel.handle(PlankEvent.Finish) }
		viewModel.uiState.onEach {
			if (it is PlankState.Content) {
				if (timer == null) {
					showDialog()
				}
				binding.countTextView.text = it.left.toString()
				if (it.left <= 0)
					viewModel.handle(PlankEvent.Finish)
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)

		return binding.root
	}

	private fun showDialog() {
		val dialogBinding = DialogBinding.inflate(layoutInflater)
		val dialogBuilder = android.app.AlertDialog.Builder(this.requireContext()).setView(dialogBinding.root)
		val alertDialog = dialogBuilder.create()
		alertDialog.show()

		val time = (viewModel.uiState.value as PlankState.Content).left
		dialogBinding.messageTextView.text = getString(R.string.text, time)
		dialogBinding.laterButton.setOnClickListener {
			alertDialog.dismiss()
		}
		dialogBinding.goButton.setOnClickListener {
			timer = createTimer()
			(timer as CountDownTimer).start()
			alertDialog.dismiss()
		}
	}

	private fun createTimer() = object : CountDownTimer((viewModel.uiState.value as PlankState.Content).left.toLong() * SECOND, SECOND) {
		override fun onTick(millisUntilFinished: Long) {
			val secondsRemaining = millisUntilFinished / SECOND
			viewModel.handle(PlankEvent.ChangeLeftCount(secondsRemaining.toInt()))
			Log.i("plank", secondsRemaining.toString())
		}

		override fun onFinish() {}
	}
}