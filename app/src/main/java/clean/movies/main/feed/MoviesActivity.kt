package clean.movies.main.feed

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import clean.movies.main.BaseActivity
import clean.movies.main.R
import clean.movies.main.feed.domain.model.Messages
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.util.ctx
import clean.movies.main.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MoviesActivity : BaseActivity(), MoviesContract.View {

    @Inject
    internal lateinit var presenter: MoviesContract.Presenter<MoviesContract.View, MoviesContract.Interactor>

    @Inject
    internal lateinit var recyclerAdapter: MoviesRecyclerAdapterImpl

    @Inject
    internal lateinit var navigator: MoviesNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
        recyclerAdapter.setItemClickedListener { _, position -> presenter.itemClicked(position = position) }

        toolbar.title = "Popular Movies"
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)
        movies_recycler.layoutManager = LinearLayoutManager(ctx)
        movies_recycler.adapter = recyclerAdapter
    }

    override fun navigateToMovieDetails(movie: Movie) {
        navigator.toMovieDetails(this, movie)
    }

    override fun setMovies(movies: List<Movie>) {
        recyclerAdapter.setMovies(movies)
    }

    override fun showToast(@Messages.Message message: Int) {
        when (message) {
            Messages.SHOW_ERROR -> toast(R.string.error_message)
        }
    }

}
