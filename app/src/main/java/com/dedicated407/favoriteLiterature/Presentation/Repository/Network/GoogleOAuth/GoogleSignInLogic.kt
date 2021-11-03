package com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class GoogleSignInLogic {
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    fun buildGoogleClient(activity: Activity) : Intent {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
        return mGoogleSignInClient.signInIntent
    }

    fun handleSignInResult(result: Intent)
            = handleSignIn(GoogleSignIn.getSignedInAccountFromIntent(result))

    private fun handleSignIn(task: Task<GoogleSignInAccount>) = try {
        val account = task.getResult(ApiException::class.java)
        arrayOf(
            account.email!!,
            account.givenName!!,
            account.familyName!!
        )
    } catch (e: Throwable) {
        e.printStackTrace()
        null
    }
}