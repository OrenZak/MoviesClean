package clean.movies.main.info

import clean.movies.main.feed.domain.model.ReviewData

interface ReviewsRecyclerAdapter {

    fun setReviews(reviews: List<ReviewData>)
}