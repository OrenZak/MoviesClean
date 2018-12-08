package clean.movies.main.util


import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewTreeObserver

val View.screenWidth: Int get() = resources.displayMetrics.widthPixels

val View.screenHeight: Int get() = resources.displayMetrics.heightPixels

val View.ctx: Context get() = context

val AppCompatActivity.ctx : Context get() = this

inline fun <T: View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}