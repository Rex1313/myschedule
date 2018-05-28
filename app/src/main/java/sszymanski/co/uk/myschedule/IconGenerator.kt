package sszymanski.co.uk.myschedule

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import sszymanski.co.uk.myschedule.models.CleaningEvent

/**
 * Created by rex on 28/05/2018.
 */
class IconGenerator {

    companion object {
        fun generateCalendarIcon(context: Context, list: List<CleaningEvent>): Drawable {
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            val whitePaint = Paint(Paint.ANTI_ALIAS_FLAG)
            whitePaint.color = Color.WHITE
            val colour = context.getColor(R.color.calendar_icon)
            paint.color = colour
            val alpha = ((255 / 8 * list.size))
            paint.alpha = alpha
            val bitmap = Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawColor(Color.WHITE)
            canvas.drawCircle(bitmap.width/2f, bitmap.height/2f, bitmap.height/3f, paint)
            return BitmapDrawable(context.resources, bitmap)
        }
    }
}