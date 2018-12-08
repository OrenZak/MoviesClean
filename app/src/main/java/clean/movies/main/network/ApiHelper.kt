package clean.movies.main.network

import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.network.request.MovieRequest
import clean.movies.main.network.response.MovieListsResponse
import io.reactivex.Flowable
import io.reactivex.Single

interface ApiHelper {

    fun getMovieDetails(request: MovieRequest.GetMovieDetails): Single<Movie>

    fun getPopularMovies(request: MovieRequest.GetPopularMovies): Flowable<MovieListsResponse>

    fun searchMovies(request: MovieRequest.SearchMovies): Flowable<MovieListsResponse>

}