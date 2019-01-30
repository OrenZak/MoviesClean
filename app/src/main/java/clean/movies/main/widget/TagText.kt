package clean.movies.main.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.widget.TextView
import clean.movies.main.R

internal class TagText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : TextView(context, attrs, defStyleAttr, defStyleRes) {



    init {
        minimumWidth = resources.getDimensionPixelSize(R.dimen.min_tag_width)
        minimumHeight = resources.getDimensionPixelSize(R.dimen.min_tag_height)
        setBackgroundResource(R.drawable.tag_shape)
        maxLines = 1
        gravity = CENTER
        setSingleLine()
        setTextColor(Color.BLACK)
        val textPaddingHeight = resources.getDimensionPixelSize(R.dimen.tag_padding_height)
        val textPaddingWidth = resources.getDimensionPixelSize(R.dimen.tag_padding_width)
        setPadding(textPaddingWidth, textPaddingHeight, textPaddingWidth, textPaddingHeight)
    }


    fun setText(text: String) : TagText {
        this.text = text
        return this
    }
}