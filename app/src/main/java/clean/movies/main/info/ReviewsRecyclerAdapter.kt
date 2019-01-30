package clean.movies.main.info

import clean.movies.main.feed.domain.model.ReviewsResult

interface ReviewsRecyclerAdapter {

    fun setReviews(reviews: ReviewsResult?)
}