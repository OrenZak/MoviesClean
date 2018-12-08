package clean.movies.main.feed.domain.model

import android.support.annotation.IntDef

class Messages {

    @IntDef(POPULAR_CLICKED, SHOW_ERROR)
    @Retention(AnnotationRetention.SOURCE)
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
    annotation class Message

    companion object {
        const val POPULAR_CLICKED = 0x00000001
        const val SHOW_ERROR = 0x00000002
    }
}
