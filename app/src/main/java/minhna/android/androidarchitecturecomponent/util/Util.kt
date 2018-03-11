package minhna.android.androidarchitecturecomponent.util

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.WriterException
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import minhna.android.androidarchitecturecomponent.R

/**
 * Created by minhnguyen on 12/4/17.
 */
object Util {
    @Throws(WriterException::class)
    fun convertTextToQRImage(context: Context, value: String): Deferred<Bitmap?> {
        return async(CommonPool) {
            val QRcodeWidth = 125
            val bitMatrix: BitMatrix
            try {
                bitMatrix = MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE,
                        QRcodeWidth, QRcodeWidth, null
                )

            } catch (Illegalargumentexception: IllegalArgumentException) {
                return@async null
            }

            val bitMatrixWidth = bitMatrix.width

            val bitMatrixHeight = bitMatrix.height

            val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)

            for (y in 0 until bitMatrixHeight) {
                val offset = y * bitMatrixWidth

                for (x in 0 until bitMatrixWidth) {

                    pixels[offset + x] = if (bitMatrix.get(x, y))
                        ContextCompat.getColor(context, R.color.black)
                    else
                        ContextCompat.getColor(context, R.color.white)
                }
            }
            val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)

            bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
            bitmap
        }
    }

    @Throws(WriterException::class)
    fun encodeAsBitmap(context: Context, str: String): Deferred<Bitmap?> {
        return async(CommonPool) {
            var result: BitMatrix
            val width = 400
            val height = width
            val black = ContextCompat.getColor(context, R.color.black)
            val white = ContextCompat.getColor(context, R.color.white)

            try {
                result = MultiFormatWriter().encode(str,
                        BarcodeFormat.QR_CODE, width, height, null)
            } catch (iae: IllegalArgumentException) {
                // Unsupported format
                return@async null
            }

            val w = result.width
            val h = result.height
            val pixels = IntArray(w * h)
            for (y in 0 until h) {
                val offset = y * w
                for (x in 0 until w) {
                    pixels[offset + x] = if (result.get(x, y)) black else white
                }
            }
            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, width, 0, 0, w, h)
            bitmap
        }
    }

    //Higher - Order function
    /////////////////////////////////////////////////////////
    fun logFunction(tag: String, func: () -> Int) {
        Log.d(tag, "before executing function")
        val valueReturn = func()
        Log.d(tag, "Log return value: " + valueReturn)
        Log.d(tag, "after executing function")
    }

    fun logFunction2(tag: String, func: (String) -> Unit) {
        Log.d(tag, "before executing function")
        func(tag)
        Log.d(tag, "after executing function")
    }
    ////////////////////////////////////////////////////////

    fun logExecution(tag: String, message: String?): Int {
        message?.let {
            return Log.v(tag, message)
        }
        return -1
    }

}