package com.dedicated407.favoriteLiterature.Presentation.Views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dedicated407.favoriteLiterature.DI.ServiceLocator
import com.dedicated407.favoriteLiterature.Domain.Model.Role
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.MainActivity
import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth.GoogleLauncher
import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth.GoogleSignInSignOutLogic
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.AuthorizationViewModel
import com.dedicated407.favoriteLiterature.R
import com.dedicated407.favoriteLiterature.databinding.AuthorizationFragmentBinding

class AuthorizationFragment : Fragment() {
    private lateinit var mViewModel: AuthorizationViewModel
    private lateinit var mBinding: AuthorizationFragmentBinding
    private lateinit var mGoogleLauncher: GoogleLauncher
    private lateinit var mSharedPreferences: SharedPreferences
    private var mGoogleSignInLogic: GoogleSignInSignOutLogic = ServiceLocator.getInstance().getGoogleSignIn()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSharedPreferences = requireContext().getSharedPreferences("AuthKey", Context.MODE_PRIVATE)

        mViewModel.lastSigned(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mGoogleSignInLogic.buildGoogleClient(requireActivity())

        mGoogleLauncher = GoogleLauncher(
            requireActivity().activityResultRegistry,
            ::onResultListener
        )

        lifecycle.addObserver(mGoogleLauncher)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = AuthorizationFragmentBinding.inflate(layoutInflater, container, false)

        mBinding.btnAuth.setOnClickListener {
            if (mBinding.inputAuthLogin.text.toString().isNotEmpty() &&
                mBinding.inputAuthPassword.text.toString().isNotEmpty()
            ) {
                mViewModel.authenticationUser(
                    mBinding.inputAuthLogin.text.toString(),
                    mBinding.inputAuthPassword.text.toString()
                ).observe(viewLifecycleOwner) {
                        user -> checkUserRole(user)
                        mSharedPreferences.edit {
                            putString("Login", user?.login)
                        }
                }
            } else {
                Toast.makeText(context, "Incorrect data!",Toast.LENGTH_SHORT).show()
            }
        }

        mBinding.btnAuthGoogle.setOnClickListener {
            mGoogleLauncher.launchGoogleSignIn(mGoogleSignInLogic.signInIntent)
        }

        mViewModel = ViewModelProvider(this)[AuthorizationViewModel::class.java]

        return mBinding.root
    }

    private fun onResultListener(intent: Intent?) {
        checkUserRole(mViewModel.onResultListener(intent))
    }

    private fun checkUserRole(user: User?) {
        if (user != null) {
            val bottomNav = (requireActivity() as MainActivity)
                .binding
                .bottomNavigation

            bottomNav.visibility = View.VISIBLE

            if (user.role != Role.User) {
                bottomNav.menu.findItem(R.id.add_book_fragment).isVisible = true
            }

            mViewModel.authenticationUser(user.login!!, "")
            mSharedPreferences.edit {
                putString("Login", user.login!!)
            }

            view?.findNavController()?.navigate(
                AuthorizationFragmentDirections.actionAuthToMyAcc()
            )
        }
    }

}