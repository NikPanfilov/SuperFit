package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.crunch.presentation.CrunchRouter
import com.nikpanfilov.superfit.exercises.presentation.ExercisesRouter
import com.nikpanfilov.superfit.image.presentation.ImageRouter
import com.nikpanfilov.superfit.imagelist.presentation.ImageListRouter
import com.nikpanfilov.superfit.lab2.navigation.CrunchRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.ExercisesRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.ImageListRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.ImageRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.LoginRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.MainActivityRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.MainRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.MyBodyRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.PasswordRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.PlankRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.PushUpsRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.ResultRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.RunningRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.SignUpRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.SquatsRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.StatisticsRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.TrainProgressRouterImpl
import com.nikpanfilov.superfit.lab2.presentation.MainActivityRouter
import com.nikpanfilov.superfit.main.presentation.MainRouter
import com.nikpanfilov.superfit.mybody.presentation.MyBodyRouter
import com.nikpanfilov.superfit.plank.presentation.PlankRouter
import com.nikpanfilov.superfit.pushups.presentation.PushUpsRouter
import com.nikpanfilov.superfit.result.presentation.ResultRouter
import com.nikpanfilov.superfit.running.presentation.RunningRouter
import com.nikpanfilov.superfit.signin.presentation.LoginRouter
import com.nikpanfilov.superfit.signin.presentation.PasswordRouter
import com.nikpanfilov.superfit.signup.presentation.SignUpRouter
import com.nikpanfilov.superfit.squats.presentation.SquatsRouter
import com.nikpanfilov.superfit.statistics.presentation.StatisticsRouter
import com.nikpanfilov.superfit.trainprogress.presentation.TrainProgressRouter
import org.koin.dsl.module

val routerModule = module {
	factory<MainActivityRouter> { MainActivityRouterImpl(get()) }

	factory<SignUpRouter> { SignUpRouterImpl(get()) }
	factory<LoginRouter> { LoginRouterImpl(get()) }
	factory<PasswordRouter> { PasswordRouterImpl(get()) }

	factory<RunningRouter> { RunningRouterImpl(get()) }
	factory<CrunchRouter> { CrunchRouterImpl(get()) }
	factory<PlankRouter> { PlankRouterImpl(get()) }
	factory<PushUpsRouter> { PushUpsRouterImpl(get()) }
	factory<SquatsRouter> { SquatsRouterImpl(get()) }

	factory<ExercisesRouter> { ExercisesRouterImpl(get()) }

	factory<ImageRouter> { ImageRouterImpl(get()) }
	factory<ImageListRouter> { ImageListRouterImpl(get()) }

	factory<MainRouter> { MainRouterImpl(get()) }

	factory<MyBodyRouter> { MyBodyRouterImpl(get()) }

	factory<ResultRouter> { ResultRouterImpl(get()) }

	factory<StatisticsRouter> { StatisticsRouterImpl(get()) }
	factory<TrainProgressRouter> { TrainProgressRouterImpl(get()) }
}