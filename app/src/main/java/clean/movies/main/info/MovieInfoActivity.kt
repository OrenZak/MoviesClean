package clean.movies.main.info

import android.os.Bundle
import clean.movies.main.BaseActivity
import clean.movies.main.BuildConfig
import clean.movies.main.R
import clean.movies.main.feed.domain.model.Movie
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment

class MovieInfoActivity : BaseActivity(), YouTubePlayer.OnInitializedListener {


    companion object {
        const val MOVIE_EXTRA_KEY = "clean.movies.movie_extra"
    }

    private lateinit var youTubePlayerFragment: YouTubePlayerSupportFragment
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        movie = intent?.extras?.getParcelable(MOVIE_EXTRA_KEY) as Movie
        youTubePlayerFragment = supportFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment
        youTubePlayerFragment.initialize(BuildConfig.YOUTUBE_API_KEY, this)

    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        movie.apply {
            p1?.cueVideo(videos?.results?.get(0)?.key)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        p1?.getErrorDialog(this, 2)
    }

    override fun showToast(message: Int) {

    }

}