package ru.sample.duckapp.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.sample.duckapp.R
import ru.sample.duckapp.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()

    private val image: ShapeableImageView by lazy { findViewById(R.id.image_main) }
    private val progress: CircularProgressIndicator by lazy { findViewById(R.id.progress_main) }
    private val input: MaterialAutoCompleteTextView by lazy { findViewById(R.id.input_http_code) }
    private val button: MaterialButton by lazy { findViewById(R.id.button_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))
        model.duck.observe(this, image::setImageBitmap)
        model.error.observe(this, input::setError)
        button.setOnClickListener { model.update() }
        input.doOnTextChanged { text, _, _, _ ->
            model.error.value = null
            model.code.value = text.toString().toIntOrNull()
        }
        input.setOnEditorActionListener { _, action, _ ->
            when (action) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    button.callOnClick()
                    true
                }

                else -> false
            }
        }
        model.onUpdateStart = { progress.visibility = View.VISIBLE }
        model.onUpdateEnd = { progress.visibility = View.INVISIBLE }
        button.callOnClick()
    }
}