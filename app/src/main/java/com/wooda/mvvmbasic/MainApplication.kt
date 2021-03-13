package com.wooda.mvvmbasic

import android.app.Application
import com.wooda.mvvmbasic.utils.BaseActivity
import com.wooda.mvvmbasic.utils.BaseFragment
import com.wooda.mvvmbasic.utils.BaseViewModel
import com.wooda.mvvmbasic.utils.logger.ILogger
import com.wooda.mvvmbasic.utils.logger.SimpleLogger
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [LoggerModule::class])
interface ApplicationComponent {
    val logger: SimpleLogger

    fun inject(activity: BaseActivity)
    fun inject(fragment: BaseFragment)
}

@Module
abstract class LoggerModule {
    @Binds
    abstract fun simpleLogger(logger: SimpleLogger): ILogger
}

class MainApplication: Application() {

    val appComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
//        Logger.d("Application is created")
    }
}