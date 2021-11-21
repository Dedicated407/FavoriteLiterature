package com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class GoogleSignInSignOutLogic {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    public val signInIntent: Intent
    get() = mGoogleSignInClient.signInIntent

    fun buildGoogleClient(activity: Activity) {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
    }

    fun signOut() = mGoogleSignInClient.signOut()

    fun getLastSignedInAcc(activity: Activity) = GoogleSignIn.getLastSignedInAccount(activity)

    fun handleSignInResult(result: Intent)
            = handleSignIn(GoogleSignIn.getSignedInAccountFromIntent(result))

    private fun handleSignIn(task: Task<GoogleSignInAccount>) = try {
        val account = task.getResult(ApiException::class.java)
        SignInResult(
            account.email!!,
            account.givenName!!,
            account.familyName!!
        )
    } catch (e: Throwable) {
        e.printStackTrace()
        println(e.localizedMessage)
        null
    }
}

data class SignInResult(
    val email: String,
    val userName: String,
    val userSurname: String
)