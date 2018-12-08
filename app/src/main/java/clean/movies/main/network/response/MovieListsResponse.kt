package clean.movies.main.network.response

import clean.movies.main.feed.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieListsResponse(
//                            @SerializedName("page")
                            var page: Int = 0,

                            @SerializedName("results")
                            var movies: List<Movie>)