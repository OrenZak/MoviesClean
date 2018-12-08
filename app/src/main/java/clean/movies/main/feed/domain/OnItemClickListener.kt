package clean.movies.main.feed.domain

import android.view.View

interface OnItemClickListener {

    fun onItemCLicked(view: View, position: Int)
}