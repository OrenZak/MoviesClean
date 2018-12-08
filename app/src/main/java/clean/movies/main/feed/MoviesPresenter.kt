package clean.movies.main.feed

import clean.movies.main.BasePresenterImpl
import clean.movies.main.feed.domain.model.Messages
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class MoviesPresenter<V : MoviesContract.View, I : MoviesContract.Interactor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenterImpl<V, I>(interactor, schedulerProvider, compositeDisposable), MoviesContract.Presenter<V, I> {

    private var pageNumber: Int = 0
    private lateinit var movies: List<Movie>

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getPopularMovies()
    }

    override fun itemClicked(position: Int) {
        val movie = movies[position]
        interactor?.let { outerIt ->
            compositeDisposable.add(
                    outerIt.getMovieDetails(movie.id.toString())
                            .compose(schedulerProvider.ioToMainSingle())
                            .subscribe({
                                view?.navigateToMovieDetails(it)
                            }, {
                                it.printStackTrace()
                                view?.showToast(Messages.SHOW_ERROR)
                            })
            )
        }
    }

    override fun getPopularMovies() {
        interactor?.let {
            compositeDisposable.add(it.getPopularMovies()
                    .compose(schedulerProvider.ioToMainFlowable())
                    .subscribe({
                        pageNumber = it.page
                        movies = it.movies
                        view?.setMovies(movies)
                    }, {
                        it.printStackTrace()
                        view?.showToast(Messages.SHOW_ERROR)
                    })
            )
        }
    }
}