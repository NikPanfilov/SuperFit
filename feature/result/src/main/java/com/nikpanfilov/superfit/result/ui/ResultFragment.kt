package com.nikpanfilov.superfit.result.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nikpanfilov.superfit.extensions.setOnBackPressedListener
import com.nikpanfilov.superfit.result.presentation.ResultEvent
import com.nikpanfilov.superfit.result.presentation.ResultViewModel
import com.nikpanfilov.superfit.success.R
import com.nikpanfilov.superfit.success.databinding.FragmentResultBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultFragment : Fragment() {

	companion object {

		private const val EXERCISE = "exercise"
		private const val LEFT = "left"
		fun newInstance(@StringRes exercise: Int, left: Int) = ResultFragment().apply {
			this.left = left
			this.exercise = exercise
			arguments = bundleOf(EXERCISE to exercise, LEFT to left)
		}
	}

	private var exercise: Int? = null
	private var left: Int? = null

	private val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<ResultViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding.goHomeButton.setOnClickListener { viewModel.handle(ResultEvent.NavigateToMain) }
		setOnBackPressedListener { viewModel.handle(ResultEvent.NavigateToMain) }
		binding.titleTextView.text = exercise?.let { getString(it) }

		if (left != null && left!! > 0) {
			binding.successImageView.visibility = View.INVISIBLE
			binding.countsLeftTextView.visibility = View.VISIBLE
			binding.countsLeftTextView.text = getString(R.string.left, left.toString())
		} else {
			binding.successImageView.visibility = View.VISIBLE
			binding.countsLeftTextView.visibility = View.INVISIBLE
		}

		return binding.root
	}
}