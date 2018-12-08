package clean.movies.main.data.movies

import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.network.response.MovieListsResponse
import io.reactivex.Flowable
import io.reactivex.Single

interface MoviesDataSource {

    fun getMovieDetails(id: String): Single<Movie>

    fun getPopularMoviesMovies(): Flowable<MovieListsResponse>

    fun searchMovie(query: String): Flowable<MovieListsResponse>
}