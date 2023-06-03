package com.nikpanfilov.superfit.signup.data.mapper

import com.nikpanfilov.superfit.signup.data.dto.RegistrationBodyDto
import com.nikpanfilov.superfit.signup.domain.entity.RegistrationBody

internal fun RegistrationBody.toDto() = RegistrationBodyDto(
	login = login,
	password = password
)