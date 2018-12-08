package clean.movies.main.feed

import clean.movies.main.BaseActivity
import clean.movies.main.feed.domain.model.Movie

interface MoviesNavigator {

    fun toMovieDetails(activity: BaseActivity, movie: Movie)
}