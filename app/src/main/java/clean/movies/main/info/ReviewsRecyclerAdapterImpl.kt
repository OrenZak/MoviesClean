package clean.movies.main.info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import clean.movies.main.R
import clean.movies.main.feed.domain.model.ReviewData
import clean.movies.main.feed.domain.model.ReviewsResult
import javax.inject.Inject

class ReviewsRecyclerAdapterImpl @Inject constructor() : RecyclerView.Adapter<ReviewsRecyclerAdapterImpl.ViewHolder>(), ReviewsRecyclerAdapter {

    private var reviews: ArrayList<ReviewData>? = null

    override fun setReviews(reviews: ReviewsResult?) {
        reviews?.results?.forEach { review ->
            this.reviews?.add(review) ?: run {
                this.reviews = arrayListOf(review)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsRecyclerAdapterImpl.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_review, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = reviews?.size ?: 0

    override fun onBindViewHolder(holder: ReviewsRecyclerAdapterImpl.ViewHolder, position: Int) {
        val review = reviews?.get(position)
        review?.apply {
            holder.authorName.text = author
            holder.reviewContent.text = content
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorName: TextView = itemView.findViewById(R.id.author_name)
        val reviewContent: TextView = itemView.findViewById(R.id.review_content)
        var isOpen = false

        init {
            reviewContent.setOnClickListener {
                when (isOpen) {
                    true ->  { reviewContent.maxLines = Int.MAX_VALUE
                                isOpen = true}
                    else -> {
                        reviewContent.maxLines = 3
                    }
                }
                reviewContent.requestLayout()
            }
        }
    }
}