package com.nikpanfilov.superfit.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.nikpanfilov.superfit.signup.databinding.FragmentSignUpBinding
import com.nikpanfilov.superfit.signup.presentation.SignUpEvent
import com.nikpanfilov.superfit.signup.presentation.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

	companion object {

		fun newInstance() = SignUpFragment()
		private const val PASSWORD_LENGTH = 4
	}

	private val binding by lazy { FragmentSignUpBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<SignUpViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.signUpButton.setOnClickListener {
			if (isFieldsFilled())
				viewModel.handle(SignUpEvent.SignUp)
		}

		binding.toSignInButton.setOnClickListener { viewModel.handle(SignUpEvent.NavigateToSignIn) }

		binding.userNameEditText.doAfterTextChanged {
			viewModel.handle(SignUpEvent.UsernameChange(it.toString()))
		}
		binding.emailEditText.doAfterTextChanged { viewModel.handle(SignUpEvent.EmailChange(it.toString())) }
		binding.passwordEditText.doAfterTextChanged { viewModel.handle(SignUpEvent.CodeChange(it.toString())) }

		return binding.root
	}

	private fun isFieldsFilled() =
		binding.userNameEditText.text.isNotEmpty()
			&& binding.emailEditText.text.contains("@")
			&& binding.passwordEditText.text.length == PASSWORD_LENGTH
			&& binding.passwordEditText.text.toString() == binding.passwordRepeatEditText.text.toString()
}