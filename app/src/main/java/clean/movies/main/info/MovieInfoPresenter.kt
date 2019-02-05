package clean.movies.main.info

import android.content.Intent
import android.support.design.widget.AppBarLayout
import clean.movies.main.BasePresenterImpl
import clean.movies.main.feed.domain.model.Messages
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class MovieInfoPresenter<V : MovieInfoContract.View, I : MovieInfoContract.Interactor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenterImpl<V, I>(interactor, schedulerProvider, compositeDisposable), MovieInfoContract.Presenter<V, I> {


    private var movie: Movie? = null
    private var youtubeInitSucceed = false
    private var videoCued = false

    override fun setIntent(intent: Intent) {
        interactor?.let {
            compositeDisposable.add(it.parseMovie(intent)
                    .compose(schedulerProvider.ioToMainSingle())
                    .subscribe({ movie ->
                        this.movie = movie
                        setMovieDetails(movie)
                        if (youtubeInitSucceed && !videoCued) cueVideo()
                    }, {
                        view?.showToast(Messages.SHOW_ERROR)
                    })
            )
        }
    }

    private fun cueVideo() {
        movie?.videos?.results?.get(0)?.apply {
            key?.let {
                videoCued = true
                view?.cueVideo(it)
            }
        }
    }

    private fun setMovieDetails(movie: Movie?) {
        movie?.apply {
            view?.apply {
                setMovieTitle(title)
                setReleaseDate(releaseDate)
                setRate("$voteAverage / 10")
                setOverview(overview)
                genres?.forEachIndexed { index, genreData ->
                    addTag(genreData.name, index == genres!!.size)
                }
                reviews?.let {
                    it.results?.let { reviewDataList ->
                        setReviews(reviewDataList)
                    }
                }
            }
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        view?.apply {
            if (Math.abs(verticalOffset) == appBarLayout.totalScrollRange) {
                setToolbarTitle(movie?.title)
                setMovieTitleVisibility(false)
            } else {
                setToolbarTitle("")
                setMovieTitleVisibility(true)
            }
        }
    }

    override fun shareClicked() {

    }


    override fun onYoutubeInitSuccess() {
        youtubeInitSucceed = true
        if (videoCued.not()) cueVideo()
    }
}