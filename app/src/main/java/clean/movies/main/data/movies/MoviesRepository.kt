package clean.movies.main.data.movies

import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.network.response.MovieListsResponse
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

internal class MoviesRepository @Inject constructor(@Local private val local: MoviesDataSource,
                                                    @Remote private val remote: MoviesDataSource) : MoviesDataSource {


    override fun getMovieDetails(id: String): Single<Movie> = remote.getMovieDetails(id)

    override fun getPopularMoviesMovies(): Flowable<MovieListsResponse> = remote.getPopularMoviesMovies()

    override fun searchMovie(query: String): Flowable<MovieListsResponse> = remote.searchMovie(query)

}