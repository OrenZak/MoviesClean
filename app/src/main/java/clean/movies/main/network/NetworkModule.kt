package clean.movies.main.network

import clean.movies.main.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Provides
    @Singleton
    internal fun bindApiHelper(): ApiHelper = ApiHelperImpl(BuildConfig.MOVIES_DB_API_KEY)
}