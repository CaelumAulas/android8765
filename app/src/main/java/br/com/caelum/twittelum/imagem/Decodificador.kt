package br.com.caelum.twittelum.imagem

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

object Decodificador {

    fun decodificaDaBase64ParaBitmap(base64: String): Bitmap {
        val decode = Base64.decode(base64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decode, 0, decode.size)
    }
}
