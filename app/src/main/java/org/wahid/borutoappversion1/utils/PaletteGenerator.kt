package org.wahid.borutoappversion1.utils
//
//import android.content.Context
//import android.graphics.BitmapFactory
//import androidx.palette.graphics.Palette
//import coil3.Bitmap
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okio.IOException
//
//object PaletteGenerator {
//
//    private val client = OkHttpClient()
//
//    suspend fun loadBitmapImage(
//        imageUrl: String,
//    ): Bitmap? = withContext(Dispatchers.IO) {
//        try {
//            val request = Request.Builder().url(imageUrl).build()
//            client.newCall(request = request).execute().use { response ->
//                if (!response.isSuccessful) {
//                    throw IOException("Unexpected code $response")
//                }
//                val inputStream = response.body?.byteStream()
//                return@withContext BitmapFactory.decodeStream(inputStream)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }
//
//    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {
//        return mapOf(
//            "vibrant" to parseColorSwatch(
//                color = Palette.from(bitmap).generate().vibrantSwatch
//            ),
//            "darkVibrant" to parseColorSwatch(
//                color = Palette.from(bitmap).generate().darkVibrantSwatch
//            ),
//            "onDarkVibrant" to parseBodyColor(
//                color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
//            )
//        )
//    }
//
//    fun parseColorSwatch(color: Palette.Swatch?): String {
//        return if (color != null) {
//            val parsedColor = Integer.toHexString(color.rgb)
//            return "$parsedColor"
//        } else {
//            "#000000"
//        }
//    }
//
//    fun parseBodyColor(color: Int?): String {
//        return if (color != null) {
//            val parsedColor = Integer.toHexString(color)
//            "$parsedColor"
//        } else {
//            "#FFFFFF"
//        }
//    }
//
//}