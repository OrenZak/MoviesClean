package clean.movies.main.info.domain.interactor

import android.content.Intent
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.info.MovieInfoContract
import clean.movies.main.info.MovieInfoContract.Companion.MOVIE_EXTRA_KEY
import io.reactivex.Single
import javax.inject.Inject

internal class MovieInfoInteractorImpl @Inject constructor() : MovieInfoContract.Interactor{



    override fun parseMovie(intent: Intent?): Single<Movie> {
        val movie = intent?.extras?.getParcelable(MOVIE_EXTRA_KEY) as Movie
        return Single.just(movie)
    }

    override fun shareMovie(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}