package clean.movies.main.network

import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.network.request.MovieRequest
import clean.movies.main.network.response.MovieListsResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(@ApiKey private val apiKey: String) : ApiHelper {

    override fun getMovieDetails(request: MovieRequest.GetMovieDetails): Single<Movie> =
            Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIES + "/{id}")
                    .addPathParameter("id", request.id)
                    .addQueryParameter("append_to_response", "videos,reviews")
                    .addQueryParameter("api_key", apiKey)
                    .build()
                    .getObjectSingle(Movie::class.java)

    override fun getPopularMovies(request: MovieRequest.GetPopularMovies): Flowable<MovieListsResponse> =
            Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_POPULAR_MOVIES)
                    .addQueryParameter("api_key", apiKey)
                    .build()
                    .getObjectFlowable(MovieListsResponse::class.java)

    override fun searchMovies(request: MovieRequest.SearchMovies): Flowable<MovieListsResponse> =
            Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SEARCH_MOVIE)
                    .addQueryParameter("api_key", apiKey)
                    .addQueryParameter("query", request.query)
                    .build()
                    .getObjectFlowable(MovieListsResponse::class.java)
}