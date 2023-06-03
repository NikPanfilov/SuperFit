package com.nikpanfilov.superfit.signin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.nikpanfilov.superfit.signin.databinding.FragmentLoginBinding
import com.nikpanfilov.superfit.signin.presentation.LoginEvent
import com.nikpanfilov.superfit.signin.presentation.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

	companion object {

		fun newInstance() = LoginFragment()
	}

	private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<LoginViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.buttonSignIn.setOnClickListener { viewModel.handleEvent(LoginEvent.NavigateToPassword) }
		binding.buttonToSignUp.setOnClickListener { viewModel.handleEvent(LoginEvent.NavigateToSignUp) }
		binding.editTextLogin.doAfterTextChanged {
			binding.buttonSignIn.isEnabled = !it.isNullOrEmpty()
			viewModel.handleEvent(LoginEvent.LoginChange(it.toString()))
		}

		return binding.root
	}
}