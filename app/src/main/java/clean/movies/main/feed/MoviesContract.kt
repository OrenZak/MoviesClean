package clean.movies.main.feed

import clean.movies.main.BaseInteractor
import clean.movies.main.BasePresenter
import clean.movies.main.BaseView
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.network.response.MovieListsResponse
import io.reactivex.Flowable
import io.reactivex.Single

interface MoviesContract {

    interface Presenter<V : View, I : Interactor> : BasePresenter<V, I> {

        fun itemClicked(position : Int)

        fun getPopularMovies()
    }

    interface View : BaseView {

        fun setMovies(movies : List<Movie>)

        fun navigateToMovieDetails(movie: Movie)
    }

    interface Interactor : BaseInteractor {

        fun getMovieDetails(id : String) : Single<Movie>

        fun getPopularMovies() : Flowable<MovieListsResponse>

        fun searchMovies(query: String) : Flowable<MovieListsResponse>
    }
}