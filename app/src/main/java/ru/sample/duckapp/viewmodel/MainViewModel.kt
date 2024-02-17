package ru.sample.duckapp.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.await
import ru.sample.duckapp.eo.RemoteImage
import ru.sample.duckapp.infra.Api

class MainViewModel : ViewModel() {
    val duck: MutableLiveData<Bitmap> = MutableLiveData()

    fun update() {
        viewModelScope.launch {
            val duck = Api.ducksApi.getRandomDuck().await()
            val image = RemoteImage(duck.url())
            val bitmap = image.download()
            this@MainViewModel.duck.value = bitmap
        }
    }
}