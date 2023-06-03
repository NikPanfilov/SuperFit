package com.nikpanfilov.superfit.signin.presentation

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PasswordViewModel(
	private val login: String,
	private val router: PasswordRouter,
	private val signInUseCase: SignInUseCase,
	private val getTokenUseCase: RefreshTokenUseCase,
	private val saveTokenUseCase: SaveTokenUseCase,
	private val saveUserDataUseCase: SaveUserDataUseCase
) : ViewModel() {

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		//val content = _stateFlow.value as? SignInState.Content ?: return@CoroutineNetworkExceptionHandler
		//_stateFlow.value = content.copy(sendState = SignInSendState.Error(code))
	}

	val uiState = MutableStateFlow(PasswordState(""))

	private companion object {

		const val PASSWORD_LENGTH = 4
	}

	fun handleEvent(event: PasswordEvent) {
		when (event) {
			PasswordEvent.NavigateBack -> navigateBack()
			is PasswordEvent.AddNumber -> addNumber(event.number)
		}
	}

	private fun navigateBack() {
		router.navigateBack()
	}

	private fun addNumber(number: String) {
		uiState.value = PasswordState(code = uiState.value.code + number)
		if (uiState.value.code.length == PASSWORD_LENGTH)
			logIn(uiState.value.code)
	}

	private fun logIn(password: String) {
		viewModelScope.launch(sendErrorHandler) {
			val refresh = signInUseCase(AuthCredential(email = login, password = password))
			val access = getTokenUseCase(refresh.refreshToken)
			saveTokenUseCase(AuthTokenPair(accessToken = access.accessToken, refreshToken = refresh.refreshToken))
			saveUserDataUseCase(UserData(login, password))
			router.navigateToMain()
		}
	}
}