package com.dedicated407.favoriteLiterature.Presentation.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dedicated407.favoriteLiterature.Domain.Model.Role
import com.dedicated407.favoriteLiterature.MainActivity
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserResponse
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.AuthorizationViewModel
import com.dedicated407.favoriteLiterature.R
import com.dedicated407.favoriteLiterature.databinding.AuthorizationFragmentBinding

class AuthorizationFragment : Fragment() {
    private lateinit var mViewModel: AuthorizationViewModel
    private lateinit var mBinding: AuthorizationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = AuthorizationFragmentBinding.inflate(layoutInflater, container, false)

        mBinding.btnAuth.setOnClickListener {
            val login = mBinding.inputAuthLogin.text.toString()
            val password = mBinding.inputAuthPassword.text.toString()
            if (login.isNotEmpty() &&
                password.isNotEmpty()
            ) {
                mViewModel.authenticationUser(
                    login,
                    password
                )
                mViewModel.mResponse.observe(viewLifecycleOwner, {
                    response ->
                    if (response != null) {
                        requireContext()
                            .getSharedPreferences("AuthKey", Context.MODE_PRIVATE).edit {
                                putString("Login", login)
                            }

                        bottomNavShow(response)
                    } else {
                        Toast.makeText(context, "Incorrect data!", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(context, "One field is empty!",Toast.LENGTH_SHORT).show()
            }
        }

        mBinding.btnAuthGoogle.setOnClickListener {

        }

        mViewModel = ViewModelProvider(this)[AuthorizationViewModel::class.java]

        return mBinding.root
    }

    private fun bottomNavShow(user: AuthUserResponse) {
        val bottomNav = (requireActivity() as MainActivity)
            .binding
            .bottomNavigation

        bottomNav.visibility = View.VISIBLE

        if (user.role != Role.User) {
            bottomNav.menu.findItem(R.id.add_book_fragment).isVisible = true
        }

        findNavController().navigate(
            AuthorizationFragmentDirections.actionAuthToMyAcc()
        )
    }

}