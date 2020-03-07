package br.com.caelum.twittelum.extensions

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

fun Bitmap.decodeTo64(): String {
    val arrayBytes = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, arrayBytes)
    val array = arrayBytes.toByteArray()
    return Base64.encodeToString(array, Base64.DEFAULT)
}
