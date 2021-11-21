package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.dedicated407.favoriteLiterature.DI.ServiceLocator
import com.dedicated407.favoriteLiterature.Domain.Model.Role
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.MainActivity
import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth.GoogleSignInSignOutLogic
import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth.SignInResult
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository
import com.dedicated407.favoriteLiterature.Presentation.Views.AuthorizationFragmentDirections
import com.dedicated407.favoriteLiterature.R

class AuthorizationViewModel : ViewModel() {
    private var mGoogleSignInLogic: GoogleSignInSignOutLogic = ServiceLocator.getInstance().getGoogleSignIn()

    fun authenticationUser(login: String, password: String) : LiveData<User?>
        = Repository.getUserRepository().loginUser(
            User(
                login = login,
                password = password
            )
        )

    fun lastSigned(activity: Activity) {
        mGoogleSignInLogic.getLastSignedInAcc(activity)?.let {
            validateUserInfo(
                SignInResult(
                    it.email!!,
                    it.givenName!!,
                    it.familyName!!
                )
            )
        }
    }

    fun onResultListener(intent: Intent?) : User? {
        if (intent == null)
            return null

        return validateUserInfo(mGoogleSignInLogic.handleSignInResult(intent))
    }

    private fun validateUserInfo(userInfo: SignInResult?) : User? {
        if (userInfo != null) {
            return User(
                login = userInfo.email,
                name = userInfo.userName,
                lastName = userInfo.userSurname,
                role = Role.User
            )
        }
        return null
    }
}