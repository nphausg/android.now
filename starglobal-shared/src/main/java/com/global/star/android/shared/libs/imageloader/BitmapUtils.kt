package com.global.star.android.shared.libs.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.BitmapCompat
import java.io.BufferedInputStream
import java.io.FileDescriptor

object BitmapUtils {

    /**
     * Get the size in bytes of a bitmap in a BitmapDrawable. Note that from Android 4.4 (KitKat)
     * onward this returns the allocated memory size of the bitmap which can be larger than the
     * actual bitmap data byte count (in the case it was re-used).
     */
    fun getBitmapSize(value: BitmapDrawable): Int {
        if (value.bitmap == null)
            return 0
        return BitmapCompat.getAllocationByteCount(value.bitmap)
    }

    fun scaleBitmap(inputStream: BufferedInputStream, width: Int, height: Int): Bitmap? {
        return BitmapFactory.Options().run {
            // inJustDecodeBounds = false
            // inSampleSize = calculateInSampleSize(this, width, height)
            // inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, this)
        }
    }

    /**
     * Calculate an inSampleSize for use in a [android.graphics.BitmapFactory.Options] object when decoding
     * bitmaps using the decode* methods from [android.graphics.BitmapFactory]. This implementation calculates
     * the closest inSampleSize that is a power of 2 and will result in the final decoded bitmap
     * having a width and height equal to or larger than the requested width and height.
     *
     * @param options   An options object with out* params already populated (run through a decode*
     * method with inJustDecodeBounds==true
     * @param reqWidth  The requested width of the resulting bitmap
     * @param reqHeight The requested height of the resulting bitmap
     * @return The value to be used for inSampleSize
     */
    private fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
    ): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize > reqHeight
                && halfWidth / inSampleSize > reqWidth
            ) {
                inSampleSize *= 2
            }
            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).
            var totalPixels = width * height / (inSampleSize * inSampleSize).toLong()
            // Anything more than 2x the requested pixels we'll sample down further
            val totalReqPixelsCap = reqWidth * reqHeight * 2.toLong()
            while (totalPixels > totalReqPixelsCap) {
                inSampleSize *= 2
                totalPixels /= 2
            }
        }
        return inSampleSize
    }

    /**
     * Decode and sample down a bitmap from a file input stream to the requested width and height.
     *
     * @param fileDescriptor The file descriptor to read from
     * @param reqWidth       The requested width of the resulting bitmap
     * @param reqHeight      The requested height of the resulting bitmap
     * @return A bitmap sampled down from the original with the same aspect ratio and dimensions
     * that are equal to or greater than the requested width and height
     */
    fun decodeSampledBitmapFromDescriptor(
        fileDescriptor: FileDescriptor?,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options)

        // Calculate inSampleSize
        options.inSampleSize =
            calculateInSampleSize(
                options,
                reqWidth,
                reqHeight
            )

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        // Try to use inBitmap
        options.inMutable = true
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options)
    }
}