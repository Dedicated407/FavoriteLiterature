package com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.*

class GoogleLauncher (
    private val registry : ActivityResultRegistry,
    private val onResultListener: (Intent?) -> Unit
): DefaultLifecycleObserver {
    lateinit var getContent : ActivityResultLauncher<Intent>

    override fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register(
            UUID.randomUUID().toString(),
            owner,
            ActivityResultContracts.StartActivityForResult()
        ) {
            onResultListener(it.data)
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        getContent.unregister()
        super.onDestroy(owner)
    }

    fun launchGoogleSignIn(intent: Intent) {
        getContent.launch(intent)
    }
}