package com.nikpanfilov.superfit.signup.domain.repository

import com.nikpanfilov.superfit.signup.domain.entity.RegistrationBody

interface SignUpRepository {

	suspend fun signUp(data: RegistrationBody)
}