package com.dedicated407.favoriteLiterature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dedicated407.favoriteLiterature.Domain.Model.Role
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository
import com.dedicated407.favoriteLiterature.databinding.MainActivityBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    val binding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        Repository(application)

        lifecycleScope.launch {
            Repository.getUserRepository().registerUser(
                User(
                    login = "dedicated",
                    password = "qwerty",
                    name = "Ilya",
                    role = Role.User
                )
            )

            Repository.getUserRepository().registerUser(
                User(
                    login = "test",
                    password = "test",
                    name = "IlyaTest"
                )
            )

            Repository.getUserRepository().registerUser(
                User(
                    login = "admin",
                    password = "admin",
                    name = "Ilya",
                    role = Role.Admin
                )
            )
        }

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHost.navController)
    }
}