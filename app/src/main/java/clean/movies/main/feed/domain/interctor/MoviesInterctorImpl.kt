package clean.movies.main.feed.domain.interctor

import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.feed.MoviesContract
import clean.movies.main.network.ApiHelper
import clean.movies.main.network.request.MovieRequest
import clean.movies.main.network.response.MovieListsResponse
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MoviesInterctorImpl @Inject constructor(private val apiHelper: ApiHelper) : MoviesContract.Interactor {

    override fun getMovieDetails(id: String): Single<Movie> {
        val values = MovieRequest.GetMovieDetails(id)
        return apiHelper.getMovieDetails(values)
    }

    override fun getPopularMovies(): Flowable<MovieListsResponse> {
        val values = MovieRequest.GetPopularMovies()
        return apiHelper.getPopularMovies(values)
    }

    override fun searchMovies(query: String): Flowable<MovieListsResponse> {
        val values = MovieRequest.SearchMovies(query)
        return apiHelper.searchMovies(values)
    }

}