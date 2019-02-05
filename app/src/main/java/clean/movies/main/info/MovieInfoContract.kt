package clean.movies.main.info

import android.content.Intent
import android.support.design.widget.AppBarLayout
import clean.movies.main.BaseInteractor
import clean.movies.main.BasePresenter
import clean.movies.main.BaseView
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.feed.domain.model.ReviewData
import io.reactivex.Single

interface MovieInfoContract {

    interface Presenter<V : View, I : Interactor> : BasePresenter<V, I> {

        fun setIntent(intent: Intent)

        fun onYoutubeInitSuccess();

        fun shareClicked()

        fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int)
    }

    interface View : BaseView {

        fun setMovieTitle(title: String)

        fun setReleaseDate(releaseDate: String)

        fun setRate(rate: String)

        fun setOverview(overview: String)

        fun addTag(tag: String, isLast: Boolean)

        fun setReviews(reviews: List<ReviewData>)

        fun cueVideo(videoUrl : String)

        fun setToolbarTitle(title: String?)

        fun setMovieTitleVisibility(isVisible: Boolean)
    }

    interface Interactor : BaseInteractor {

        fun parseMovie(intent: Intent?): Single<Movie>

        fun shareMovie(movie: Movie)
    }

    companion object {
        const val MOVIE_EXTRA_KEY = "clean.movies.movie_extra"
    }
}