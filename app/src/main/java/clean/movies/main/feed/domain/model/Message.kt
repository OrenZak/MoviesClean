package clean.movies.main.feed.domain.model

import android.support.annotation.IntDef

class Messages {

    @IntDef(SHOW_ERROR)
    @Retention(AnnotationRetention.SOURCE)
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
    annotation class Message

    companion object {
        const val SHOW_ERROR = 0x00000001
    }
}
