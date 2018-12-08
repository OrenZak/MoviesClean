package clean.movies.main

import clean.movies.main.feed.domain.model.Messages

interface BaseView {

    fun showToast(@Messages.Message message: Int)
}