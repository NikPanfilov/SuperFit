package com.nikpanfilov.superfit.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.network.token.domain.usecase.RefreshTokenUseCase
import com.nikpanfilov.network.token.domain.usecase.SaveTokenUseCase
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.login.domain.entity.AuthCredential
import com.nikpanfilov.shared.login.domain.entity.UserData
import com.nikpanfilov.shared.login.domain.usecase.SaveUserDataUseCase
import com.nikpanfilov.shared.login.domain.usecase.SignInUseCase
import com.nikpanfilov.superfit.signup.domain.entity.RegistrationBody
import com.nikpanfilov.superfit.signup.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
	private val router: SignUpRouter,
	private val signUpUseCase: SignUpUseCase,
	private val signInUseCase: SignInUseCase,
	private val getTokenUseCase: RefreshTokenUseCase,
	private val saveTokenUseCase: SaveTokenUseCase,
	private val saveUserDataUseCase: SaveUserDataUseCase
) : ViewModel() {

	val uiState = MutableStateFlow<SignUpState>(SignUpState.Initial)

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		uiState.value = SignUpState.Content("", "", "")
	}

	fun handle(event: SignUpEvent) {
		when (event) {
			SignUpEvent.SignUp            -> signUp()
			SignUpEvent.NavigateToSignIn  -> router.navigateToSignIn()
			is SignUpEvent.CodeChange     -> (uiState.value as SignUpState.Content).setCode(event.code)
			is SignUpEvent.EmailChange    -> (uiState.value as SignUpState.Content).setEmail(event.email)
			is SignUpEvent.UsernameChange -> (uiState.value as SignUpState.Content).setUserName(event.username)
		}
	}

	private fun signUp() {
		viewModelScope.launch(sendErrorHandler) {
			val state = uiState.value as SignUpState.Content
			signUpUseCase(RegistrationBody(state.email, state.pinCode))
			val refresh = signInUseCase(AuthCredential(state.email, state.pinCode))
			val access = getTokenUseCase(refresh.refreshToken)
			saveTokenUseCase(AuthTokenPair(accessToken = access.accessToken, refreshToken = refresh.refreshToken))
			saveUserDataUseCase(UserData(state.userName, state.pinCode))
			router.navigateToMain()
		}
	}

	private fun SignUpState.Content.setUserName(username: String) {
		uiState.value = SignUpState.Content(username, this.email, this.pinCode)
	}

	private fun SignUpState.Content.setEmail(email: String) {
		uiState.value = SignUpState.Content(this.userName, email, this.pinCode)
	}

	private fun SignUpState.Content.setCode(code: String) {
		uiState.value = SignUpState.Content(this.userName, this.email, code)
	}
}
