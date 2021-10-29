package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dedicated407.favoriteLiterature.MainActivity
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.AuthorizationViewModel
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

        mBinding.btnAuth.setOnClickListener { view ->
            if (mBinding.inputAuthLogin.text.toString().isNotEmpty() &&
                mBinding.inputAuthPassword.text.toString().isNotEmpty()
            ) {
                mViewModel.authenticationUser(
                    mBinding.inputAuthLogin.text.toString(),
                    mBinding.inputAuthPassword.text.toString()
                ).observe(viewLifecycleOwner) { user ->
                        if (user != null) {
                            (requireActivity() as MainActivity)
                                .binding
                                .bottomNavigation
                                .visibility = View.VISIBLE

                            view.findNavController().navigate(
                                AuthorizationFragmentDirections.actionAuthToMyAcc()
                            )
                        } else {
                            Toast.makeText(context, "Incorrect user",Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Incorrect data!",Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel = ViewModelProvider(this)[AuthorizationViewModel::class.java]

        return mBinding.root
    }

}