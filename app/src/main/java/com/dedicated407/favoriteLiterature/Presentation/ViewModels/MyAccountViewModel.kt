package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel

class MyAccountViewModel : ViewModel() {

    fun signOut(context: Context): Boolean {

        val key = context.getSharedPreferences(
            "AuthKey",
            Context.MODE_PRIVATE
        ).all["Login"]

        if (key != null) {
            context.getSharedPreferences("AuthKey", Context.MODE_PRIVATE).edit {
                putString("Login", null)
            }

            return true
        }
        return false
    }


}