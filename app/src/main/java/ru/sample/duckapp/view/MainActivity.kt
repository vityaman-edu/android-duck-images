package ru.sample.duckapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.progressindicator.CircularProgressIndicator
import ru.sample.duckapp.R
import ru.sample.duckapp.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()

    private val image: ShapeableImageView by lazy { findViewById(R.id.image_main) }
    private val button: MaterialButton by lazy { findViewById(R.id.button_main) }
    private val progress: CircularProgressIndicator by lazy { findViewById(R.id.progress_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))
        model.duck.observe(this) { bitmap ->
            image.setImageBitmap(bitmap)
            progress.visibility = View.INVISIBLE
        }
        button.setOnClickListener {
            progress.visibility = View.VISIBLE
            model.update()
        }
        button.callOnClick()
    }
}