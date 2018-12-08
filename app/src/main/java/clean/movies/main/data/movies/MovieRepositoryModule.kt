package clean.movies.main.data.movies

import clean.movies.main.data.movies.local.MovieLocalDataSource
import clean.movies.main.data.movies.remote.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class MovieRepositoryModule {

    @Binds
    @Singleton
    @Local
    internal abstract fun bindMovieLocalDataSource(@Local local: MovieLocalDataSource): MoviesDataSource

    @Binds
    @Singleton
    @Remote
    internal abstract fun bindMovieRemoteDataSource(@Remote remote: MovieRemoteDataSource): MoviesDataSource
}