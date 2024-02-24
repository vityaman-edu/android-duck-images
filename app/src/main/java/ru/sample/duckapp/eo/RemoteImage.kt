package ru.sample.duckapp.eo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class RemoteImage(private val url: URL) {
    suspend fun download(): Bitmap = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }
}