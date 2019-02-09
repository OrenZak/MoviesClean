package clean.movies.main.feed


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import clean.movies.main.BlurTransformation
import clean.movies.main.R
import clean.movies.main.feed.domain.model.Movie
import clean.movies.main.feed.domain.OnItemClickListener
import clean.movies.main.feed.domain.model.GenreConverter
import clean.movies.main.network.ApiEndPoint
import clean.movies.main.util.ctx
import clean.movies.main.util.screenWidth
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MoviesRecyclerAdapterImpl @Inject constructor() : RecyclerView.Adapter<MoviesRecyclerAdapterImpl.ViewHolder>(), MoviesRecyclerAdapter {

    private var movieList: MutableList<Movie>? = null

    private var itemClickListener: OnItemClickListener? = null

    override fun setMovies(movieList: List<Movie>) {
        movieList.forEach { movie ->
            this.movieList?.add(movie) ?: run {
                this.movieList = arrayListOf(movie)
            }
        }
        notifyDataSetChanged()
    }

    override fun setItemClickedListener(listener: (View, Int) -> Unit) {
        setListener(object : OnItemClickListener{
            override fun onItemCLicked(view: View, position: Int) {
                listener.invoke(view, position)
            }
        })
    }

    private fun setListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_movie, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int = movieList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList?.get(position)
        movie?.apply {
            Picasso.get()
                    .load("${ApiEndPoint.BASE_IMAGE_UL}$posterPath")
                    .resize(holder.imageWidth, holder.imageHeight)
                    .centerCrop()
                    .into(holder.image)

            Picasso.get()
                    .load("${ApiEndPoint.BASE_IMAGE_UL}$backdropPath")
                    .transform(BlurTransformation(ctx = holder.image.ctx))
                    .into(holder.blurImageBackground)

            holder.title.text = title

            genreIds?.let {
                 holder.genres.text = GenreConverter.getAsStringFormatted(it)
            } ?: kotlin.run {
                 holder.genres.visibility = INVISIBLE
             }
            holder.rate.text = holder.itemView.ctx.getString(R.string.avg_vote, voteAverage)
            holder.voteCount.text = holder.itemView.ctx.getString(R.string.vote_count, voteCount)
            holder.releaseDate.text = releaseDate
        }
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemCLicked(it, holder.layoutPosition)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.movie_image)
        val blurImageBackground: ImageView = itemView.findViewById(R.id.blur_image_background)
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val genres: TextView = itemView.findViewById(R.id.genres)
        val rate: TextView = itemView.findViewById(R.id.vote_avg)
        val voteCount: TextView = itemView.findViewById(R.id.vote_count)
        val releaseDate: TextView = itemView.findViewById(R.id.release_date)

        var imageWidth: Int = 0
        var imageHeight: Int = 0

        init {
            image.layoutParams.apply {
                val ratio = 1.5
                width = image.screenWidth / 3
                height = (width * ratio).toInt()

                imageWidth = width
                imageHeight = height
            }
        }
    }
}