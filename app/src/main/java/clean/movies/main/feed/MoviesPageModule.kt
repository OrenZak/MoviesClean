package clean.movies.main.feed

import clean.movies.main.di.ActivityScoped
import clean.movies.main.feed.domain.interctor.MoviesInterctorImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class MoviesPageModule {

    @Binds
    internal abstract fun bindMoviesInteractor(interactor: MoviesInterctorImpl): MoviesContract.Interactor

    @Binds
    @ActivityScoped
    internal abstract fun bindMoviesPresenter(presenter: MoviesPresenter<MoviesContract.View, MoviesContract.Interactor>): MoviesContract.Presenter<MoviesContract.View, MoviesContract.Interactor>

    @Binds
    @ActivityScoped
    internal abstract fun bindMoviesRecyclerAdapter(adapter: MoviesRecyclerAdapterImpl) : MoviesRecyclerAdapter

    @Binds
    @ActivityScoped
    internal abstract fun bindMovieNavigator(navigator: MoviesNavigatorImpl) : MoviesNavigator
}