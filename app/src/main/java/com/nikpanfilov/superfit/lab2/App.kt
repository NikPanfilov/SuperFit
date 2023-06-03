package com.nikpanfilov.superfit.lab2

import android.app.Application
import com.nikpanfilov.superfit.lab2.di.appModule
import com.nikpanfilov.superfit.lab2.di.bodyModule
import com.nikpanfilov.superfit.lab2.di.crunchModule
import com.nikpanfilov.superfit.lab2.di.exercisesModule
import com.nikpanfilov.superfit.lab2.di.imageListModule
import com.nikpanfilov.superfit.lab2.di.imageModule
import com.nikpanfilov.superfit.lab2.di.mainModule
import com.nikpanfilov.superfit.lab2.di.myBodyModule
import com.nikpanfilov.superfit.lab2.di.networkModule
import com.nikpanfilov.superfit.lab2.di.resultModule
import com.nikpanfilov.superfit.lab2.di.routerModule
import com.nikpanfilov.superfit.lab2.di.signInModule
import com.nikpanfilov.superfit.lab2.di.signUpModule
import com.nikpanfilov.superfit.lab2.di.statisticsModule
import com.nikpanfilov.superfit.lab2.di.tokenModule
import com.nikpanfilov.superfit.lab2.di.trainProgressModule
import com.nikpanfilov.superfit.lab2.di.trainingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	companion object {

		const val BACKEND = "BACKEND"
		private const val BACKEND_ENDPOINT = "http://fitness.wsmob.xyz:22169/"

		internal lateinit var INSTANCE: App
			private set
	}

	override fun onCreate() {
		super.onCreate()

		INSTANCE = this

		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)
			properties(mapOf(BACKEND to BACKEND_ENDPOINT))
			androidFileProperties()

			modules(appModule)
			modules(routerModule)
			modules(networkModule)
			modules(tokenModule)

			modules(bodyModule)
			modules(trainingsModule)

			modules(signInModule)
			modules(signUpModule)
			modules(crunchModule)
			modules(exercisesModule)
			modules(imageListModule)
			modules(imageModule)
			modules(mainModule)
			modules(myBodyModule)
			modules(resultModule)
			modules(statisticsModule)
			modules(trainProgressModule)
		}
	}
}
