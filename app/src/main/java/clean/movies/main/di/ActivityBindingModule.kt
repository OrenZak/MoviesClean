package clean.movies.main.di

import clean.movies.main.feed.MoviesActivity
import clean.movies.main.feed.MoviesPageModule
import clean.movies.main.info.MovieInfoActivity
import clean.movies.main.info.MoviesInfoModule
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(MoviesPageModule::class)])
    internal abstract fun bindMoviesFeedActivity(): MoviesActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(MoviesInfoModule::class)])
    internal abstract fun bindMoviesInfoActivity(): MovieInfoActivity
}