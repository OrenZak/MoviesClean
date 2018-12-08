package clean.movies.main.data.movies.local

import clean.movies.main.data.movies.MoviesDataSource
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.network.response.MovieListsResponse
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor() : MoviesDataSource {
    override fun getMovieDetails(id: String): Single<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPopularMoviesMovies(): Flowable<MovieListsResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchMovie(query: String): Flowable<MovieListsResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}