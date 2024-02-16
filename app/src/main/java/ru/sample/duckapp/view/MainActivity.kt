package ru.sample.duckapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sample.duckapp.R
import ru.sample.duckapp.domain.Duck
import ru.sample.duckapp.infra.Api

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))

        Api.ducksApi.getRandomDuck().enqueue((object : Callback<Duck> {
            override fun onFailure(call: Call<Duck>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to fetch a duck", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<Duck>, response: Response<Duck>) {
                val duck = response.body()!!
                Toast.makeText(this@MainActivity, "Fetched a duck $duck", Toast.LENGTH_LONG)
                    .show()
            }
        }))
    }
}