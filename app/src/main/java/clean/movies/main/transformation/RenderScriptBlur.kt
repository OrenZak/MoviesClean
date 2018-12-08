package clean.movies.main.transformation

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.*


object RenderScriptBlur {
    @Throws(RSRuntimeException::class)
    fun blur(context: Context, bitmap: Bitmap, radius: Int): Bitmap {
        var rs: RenderScript? = null
        var input: Allocation? = null
        var output: Allocation? = null
        var blur: ScriptIntrinsicBlur? = null
        try {
            rs = RenderScript.create(context)
            rs!!.messageHandler = RenderScript.RSMessageHandler()
            input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT)
            output = Allocation.createTyped(rs, input!!.type)
            blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))


            blur!!.setInput(input)
            blur.setRadius(radius.toFloat())
            blur.forEach(output)
            output!!.copyTo(bitmap)
        } finally {
            if (rs != null) {
                rs.destroy()
            }
            if (input != null) {
                input.destroy()
            }
            if (output != null) {
                output.destroy()
            }
            if (blur != null) {
                blur.destroy()
            }
        }

        return bitmap
    }
}