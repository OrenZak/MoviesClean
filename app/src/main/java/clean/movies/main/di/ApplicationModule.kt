package clean.movies.main.di

import android.app.Application
import android.content.Context
import clean.movies.main.util.SchedulerProvider
import clean.movies.main.util.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class ApplicationModule {

    //expose Application as an injectable context
    @Provides
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}