package ru.sample.duckapp.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.await
import ru.sample.duckapp.eo.RemoteImage
import ru.sample.duckapp.infra.Api
import java.io.FileNotFoundException

class MainViewModel : ViewModel() {
    var onUpdateStart: () -> Unit = {}
    var onUpdateEnd: () -> Unit = {}

    val duck: MutableLiveData<Bitmap> = MutableLiveData()
    val code: MutableLiveData<Int> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun update() {
        viewModelScope.launch {
            try {
                onUpdateStart()
                val url = when (val code = code.value) {
                    null -> Api.ducksApi.getRandomDuck().await().url()
                    else -> Api.httpCodeDuckUrl(code)
                }
                val image = RemoteImage(url)
                val bitmap = image.download()
                this@MainViewModel.duck.value = bitmap
            } catch (_: FileNotFoundException) {
                this@MainViewModel.error.value = "No such HTTP Code"
            } finally {
                onUpdateEnd()
            }
        }
    }

}