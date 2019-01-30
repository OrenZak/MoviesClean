package clean.movies.main.info

import clean.movies.main.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
internal abstract class MoviesInfoModule {

    @Binds
    @ActivityScoped
    internal abstract fun bindReviewsRecyclerAdapter(adapter: ReviewsRecyclerAdapterImpl) : ReviewsRecyclerAdapter
}