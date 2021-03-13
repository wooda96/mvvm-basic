package com.wooda.mvvmbasic

import android.app.Application
import com.wooda.mvvmbasic.utils.BaseActivity
import com.wooda.mvvmbasic.utils.Logger
import com.wooda.mvvmbasic.utils.logger.ILogger
import com.wooda.mvvmbasic.utils.logger.SimpleLogger
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [LoggerModule::class])
interface ApplicationComponent {
    fun inject(activity: BaseActivity)
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
        Logger.d("Application is created")
    }
}