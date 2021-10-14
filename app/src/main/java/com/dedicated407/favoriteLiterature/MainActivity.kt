package com.dedicated407.favoriteLiterature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository
import com.dedicated407.favoriteLiterature.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Repository(application)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(mBinding.bottomNavigation, navHost.navController)
    }
}