package clean.movies.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.renderscript.RSRuntimeException
import clean.movies.main.transformation.FastBlur
import clean.movies.main.transformation.RenderScriptBlur
import com.squareup.picasso.Transformation


class BlurTransformation @JvmOverloads constructor(private val ctx: Context, private val mRadius: Int = MAX_RADIUS, private val sampling: Int = DEFAULT_DOWN_SAMPLING) : Transformation {

    override fun transform(source: Bitmap): Bitmap {

        val scaledWidth = source.width / sampling
        val scaledHeight = source.height / sampling

        var bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        canvas.scale(1 / sampling.toFloat(), 1 / sampling.toFloat())
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        canvas.drawBitmap(source, 0f, 0f, paint)

        bitmap = try {
            RenderScriptBlur.blur(ctx, bitmap, mRadius)
        } catch (e: RSRuntimeException) {
            FastBlur.blur(bitmap, mRadius, true)
        }


        source.recycle()

        return bitmap
    }

    override fun key(): String {
        return "BlurTransformation(radius=$mRadius, sampling=$sampling)"
    }

    companion object {

        private val MAX_RADIUS = 25
        private val DEFAULT_DOWN_SAMPLING = 1
    }
}
