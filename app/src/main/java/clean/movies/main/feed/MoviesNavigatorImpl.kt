package clean.movies.main.feed

import android.content.Intent
import clean.movies.main.BaseActivity
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.info.MovieInfoActivity
import clean.movies.main.info.MovieInfoContract.Companion.MOVIE_EXTRA_KEY
import javax.inject.Inject

class MoviesNavigatorImpl @Inject constructor() : MoviesNavigator {


    override fun toMovieDetails(activity: BaseActivity, movie: Movie) {
        val intent = Intent(activity, MovieInfoActivity::class.java)
        intent.putExtra(MOVIE_EXTRA_KEY, movie)
        activity.startActivity(intent)
    }
}