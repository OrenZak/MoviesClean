package clean.movies.main.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieRequest {

    data class GetMovieDetails internal constructor(@Expose
                                                    @SerializedName("id") internal val id: String)

    class GetPopularMovies internal constructor()

    data class SearchMovies internal constructor(@Expose
                                                 @SerializedName("query") internal val query: String)
}