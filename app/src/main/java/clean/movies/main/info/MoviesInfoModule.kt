package clean.movies.main.info

import clean.movies.main.di.ActivityScoped
import clean.movies.main.info.domain.interactor.MovieInfoInteractorImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class MoviesInfoModule {

    @Binds
    internal abstract fun bindMovieInfoInteractor(interactor: MovieInfoInteractorImpl): MovieInfoContract.Interactor

    @Binds
    @ActivityScoped
    internal abstract fun bindMovieInfoPresenter(presenter: MovieInfoPresenter<MovieInfoContract.View, MovieInfoContract.Interactor>): MovieInfoContract.Presenter<MovieInfoContract.View, MovieInfoContract.Interactor>

    @Binds
    @ActivityScoped
    internal abstract fun bindReviewsRecyclerAdapter(adapter: ReviewsRecyclerAdapterImpl): ReviewsRecyclerAdapter
}