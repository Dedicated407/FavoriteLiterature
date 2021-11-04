package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.DI.ServiceLocator

class MyAccountViewModel : ViewModel() {

    fun signOut(context: Context) : Boolean {

        val key = context.getSharedPreferences(
            "AuthKey",
            Context.MODE_PRIVATE
        ).all["Login"]

        if (key != null) {
            ServiceLocator.getInstance().getGoogleSignIn().signOut()

            context.getSharedPreferences("AuthKey", Context.MODE_PRIVATE).all.clear()
            return true
        }

        return false
    }

}