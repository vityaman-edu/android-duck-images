package ru.sample.duckapp.view

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.sample.duckapp.R
import ru.sample.duckapp.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()

    private val image: ImageView by lazy { findViewById(R.id.image_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))
        model.duck.observe(this, image::setImageBitmap)
    }
}