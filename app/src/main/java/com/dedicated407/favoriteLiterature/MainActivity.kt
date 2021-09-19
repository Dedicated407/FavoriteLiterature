package com.dedicated407.favoriteLiterature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        Repository(application)
    }
}