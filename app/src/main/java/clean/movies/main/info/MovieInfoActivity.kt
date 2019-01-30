package clean.movies.main.info

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import clean.movies.main.BaseActivity
import clean.movies.main.BuildConfig
import clean.movies.main.R
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.util.afterMeasured
import clean.movies.main.util.ctx
import clean.movies.main.util.toast
import clean.movies.main.widget.TagText
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject


class MovieInfoActivity : BaseActivity(), YouTubePlayer.OnInitializedListener {


    companion object {
        const val MOVIE_EXTRA_KEY = "clean.movies.movie_extra"
    }

    private lateinit var youTubePlayerFragment: YouTubePlayerSupportFragment
    private var movie: Movie? = null

    @Inject
    internal lateinit var reviewsRecyclerAdapter: ReviewsRecyclerAdapterImpl

    private var toolbarTitle: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        toolbar.title = "This is me"
        setSupportActionBar(toolbar)
        findToolbarTextView()
        movie = intent?.extras?.getParcelable(MOVIE_EXTRA_KEY) as Movie
        youTubePlayerFragment = supportFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment
        youTubePlayerFragment.initialize(BuildConfig.YOUTUBE_API_KEY, this)
        movie?.let { movie ->
            movie_title.text = movie.title
            release_date.text = movie.releaseDate
            rate.text = "${movie.voteAverage} / 10"
            overview_content.text = movie.overview
            movie.genres?.forEachIndexed { index, genreData ->
                val tag = TagText(ctx)
                tag.setText(genreData.name)
                tags_container.addView(tag)
                if (index < movie.genres!!.size) {
                    (tag.layoutParams as LinearLayout.LayoutParams).rightMargin =
                            resources.getDimensionPixelSize(R.dimen.tag_margin_right)
                }
            }
            reviewsRecyclerAdapter.setReviews(movie.reviews)
            review_recycler.layoutManager = LinearLayoutManager(ctx)
            review_recycler.adapter = reviewsRecyclerAdapter
        }

        app_bar_layout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            movie_title.visibility =
                    if (Math.abs(verticalOffset) == appBarLayout.totalScrollRange) {
                        (toolbarTitle != movie_title.text).run {
                            toolbarTitle?.let {

                                it.text = movie_title.text
                            }
                        }
                        GONE
                    } else {
                        (!TextUtils.isEmpty(toolbar.title)).run {
                            toolbarTitle?.text = ""
                        }
                        VISIBLE
                    }
        }
        //TODO should listen for start, pause and finished.

    }

    private fun findToolbarTextView() {
        toast("toolbar.childCount=${toolbar.childCount}")
        for (i in 0 until toolbar.childCount) {
            val child = toolbar.getChildAt(i)
            // assuming that the title is the first instance of TextView
            // you can also check if the title string matches
            if (child is TextView) {
                toolbarTitle = child
                break
            }
        }
    }


    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        movie?.apply {
            p1?.cueVideo(videos?.results?.get(0)?.key)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        p1?.getErrorDialog(this, 2)
    }

    override fun showToast(message: Int) {

    }
}