package clean.movies.main.feed.domain.model

class GenreConverter {
    companion object {
        fun getGenreName(id: Int): String {
            return when (id) {
                28 -> "Action"
                12 -> "Adventure"
                16 -> "Animation"
                35 -> "Comedy"
                80 -> "Crime"
                99 -> "Documentary"
                18 -> "Drama"
                10402 -> "Music"
                9648 -> "Mystery"
                10770 -> "Romance"
                878 -> "Science Fiction"
                53 -> "Thriller"
                10752 -> "War"
                37 -> "Western"
                10751 -> "Family"
                14 -> "Fantasy"
                10769 -> "Foreign"
                36 -> "History"
                27 -> "Horror"
                else -> "Other"
            }
        }

        fun getAsStringFormatted(genreIds: List<Int>): String? {
            val movieGenres = StringBuilder()
            val genreItr = genreIds.iterator()
            while (genreItr.hasNext()) {
                val genreId = genreItr.next()
                movieGenres.append(getGenreName(genreId))
                if (genreItr.hasNext()) {
                    movieGenres.append(" | ")
                }
            }
            return movieGenres.toString()
        }
    }
}