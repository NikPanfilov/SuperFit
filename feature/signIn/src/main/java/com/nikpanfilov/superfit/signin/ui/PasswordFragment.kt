package com.nikpanfilov.superfit.signin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nikpanfilov.superfit.signin.databinding.FragmentPasswordBinding
import com.nikpanfilov.superfit.signin.presentation.PasswordEvent
import com.nikpanfilov.superfit.signin.presentation.PasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PasswordFragment : Fragment() {

	companion object {

		private const val LOGIN = "login"
		fun newInstance(login: String) = PasswordFragment().apply {
			this.login = login
			arguments = bundleOf(LOGIN to login)
		}
	}

	private var login = ""

	private val binding by lazy { FragmentPasswordBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<PasswordViewModel> {
		parametersOf(arguments?.get(LOGIN))
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.loginTextView.text = login
		binding.backButton.setOnClickListener { viewModel.handleEvent(PasswordEvent.NavigateBack) }
		for (id in binding.numButtons.referencedIds) {
			val button = binding.root.findViewById<Button>(id)
			button.setOnClickListener { viewModel.handleEvent(PasswordEvent.AddNumber(button.text.toString())) }
		}

		return binding.root
	}
}