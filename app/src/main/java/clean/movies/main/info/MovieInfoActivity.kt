package clean.movies.main.info

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import clean.movies.main.BaseActivity
import clean.movies.main.BuildConfig
import clean.movies.main.R
import clean.movies.main.feed.domain.model.ReviewData
import clean.movies.main.util.ctx
import clean.movies.main.util.toast
import clean.movies.main.widget.TagText
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject


class MovieInfoActivity : BaseActivity(), YouTubePlayer.OnInitializedListener, MovieInfoContract.View {

    @Inject
    internal lateinit var presenter: MovieInfoContract.Presenter<MovieInfoContract.View, MovieInfoContract.Interactor>

    @Inject
    internal lateinit var reviewsRecyclerAdapter: ReviewsRecyclerAdapterImpl

    private var toolbarTitle: TextView? = null
    private var youTubePlayer: YouTubePlayer? = null
    private lateinit var youTubePlayerFragment: YouTubePlayerSupportFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(toolbar)
        findToolbarTextView()
        presenter.onAttach(this)
        presenter.setIntent(intent)
        youTubePlayerFragment = supportFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment
        youTubePlayerFragment.initialize(BuildConfig.YOUTUBE_API_KEY, this)

        app_bar_layout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            presenter.onOffsetChanged(appBarLayout, verticalOffset)
        }
    }

    override fun setToolbarTitle(title: String?) {
        toolbarTitle?.text = title
    }

    override fun setMovieTitleVisibility(isVisible: Boolean) {
        movie_title.visibility = if (isVisible) VISIBLE else GONE
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
        youTubePlayer = p1
        presenter.onYoutubeInitSuccess()
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        p1?.getErrorDialog(this, 2)
    }

    override fun setMovieTitle(title: String) {
        movie_title.text = title
    }

    override fun setReleaseDate(releaseDate: String) {
        release_date.text = releaseDate
    }

    override fun setRate(rate: String) {
        rate_text.text = rate
    }

    override fun setOverview(overview: String) {
        overview_content.text = overview
    }

    override fun addTag(tag: String, isLast: Boolean) {
        val tagView = TagText(ctx)
        tagView.text = tag
        tags_container.addView(tagView)
        if (!isLast) {
            (tagView.layoutParams as LinearLayout.LayoutParams).rightMargin =
                    resources.getDimensionPixelSize(R.dimen.tag_margin_right)
        }
    }

    override fun setReviews(reviews: List<ReviewData>) {
        reviewsRecyclerAdapter.setReviews(reviews)
        review_recycler.layoutManager = LinearLayoutManager(ctx)
        review_recycler.adapter = reviewsRecyclerAdapter
    }

    override fun cueVideo(videoUrl: String) {
        youTubePlayer?.cueVideo(videoUrl)
    }

    override fun showToast(message: Int) {

    }
}