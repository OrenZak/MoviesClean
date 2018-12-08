package clean.movies.main.feed

import android.view.View
import clean.movies.main.feed.domain.model.Movie

interface MoviesRecyclerAdapter {

    fun setMovies(movieList: List<Movie>)

    fun setItemClickedListener(listener: (View, Int) -> Unit)
}