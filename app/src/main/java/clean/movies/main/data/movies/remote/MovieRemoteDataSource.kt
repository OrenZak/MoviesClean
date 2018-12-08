package clean.movies.main.data.movies.remote

import clean.movies.main.data.movies.MoviesDataSource
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.network.ApiHelper
import clean.movies.main.network.request.MovieRequest
import clean.movies.main.network.response.MovieListsResponse
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val apiHelper : ApiHelper): MoviesDataSource {

    override fun getMovieDetails(id: String): Single<Movie> = apiHelper.getMovieDetails(MovieRequest.GetMovieDetails(id))

    override fun getPopularMoviesMovies(): Flowable<MovieListsResponse> = apiHelper.getPopularMovies(MovieRequest.GetPopularMovies())

    override fun searchMovie(query: String): Flowable<MovieListsResponse> = apiHelper.searchMovies(MovieRequest.SearchMovies(query))
}