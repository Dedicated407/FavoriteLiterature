package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.DI.ServiceLocator
import com.google.android.gms.tasks.Task

class MyAccountViewModel : ViewModel() {

    fun signOut(context: Context): Task<Void>? {

        val key = context.getSharedPreferences(
            "AuthKey",
            Context.MODE_PRIVATE
        ).all["Login"]

        if (key != null) {
            context.getSharedPreferences("AuthKey", Context.MODE_PRIVATE).edit {
                putString("Login", null)
            }

            return ServiceLocator.getInstance().getGoogleSignIn().signOut()
        }
        return null
    }


}