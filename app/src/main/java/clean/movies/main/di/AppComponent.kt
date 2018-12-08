package clean.movies.main.di

import android.app.Application
import clean.movies.main.MoviesApp
import clean.movies.main.data.movies.MovieRepositoryModule
import clean.movies.main.network.ApiKey
import clean.movies.main.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules =
[(MovieRepositoryModule::class),
    (NetworkModule::class),
    (ApplicationModule::class),
    (ActivityBindingModule::class),
    (AndroidSupportInjectionModule::class)])
interface AppComponent : AndroidInjector<MoviesApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}