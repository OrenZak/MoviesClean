package clean.movies.main.network

object ApiEndPoint {
    private const val BASE_UL = "https://api.themoviedb.org/3"
    const val BASE_IMAGE_UL = "https://image.tmdb.org/t/p/w500"

    const val ENDPOINT_MOVIES = "$BASE_UL/movie"
    const val ENDPOINT_POPULAR_MOVIES = "$BASE_UL/movie/popular"
    const val ENDPOINT_SEARCH_MOVIE = "$BASE_UL/search/movie"
}