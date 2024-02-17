package ru.sample.duckapp.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.await
import ru.sample.duckapp.eo.RemoteImage
import ru.sample.duckapp.infra.Api

class MainViewModel : ViewModel() {
    val duck: LiveData<Bitmap> = liveData {
        val duck = Api.ducksApi.getRandomDuck().await()
        val image = RemoteImage(duck.url())
        val bitmap = image.download()
        emit(bitmap)
    }
}