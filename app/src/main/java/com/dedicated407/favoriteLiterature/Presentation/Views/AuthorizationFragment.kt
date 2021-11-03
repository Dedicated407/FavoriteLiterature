package com.dedicated407.favoriteLiterature.Presentation.Views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dedicated407.favoriteLiterature.Domain.Model.Role
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.MainActivity
import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth.GoogleSignInLogic
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.AuthorizationViewModel
import com.dedicated407.favoriteLiterature.R
import com.dedicated407.favoriteLiterature.databinding.AuthorizationFragmentBinding

class AuthorizationFragment : Fragment() {
    private lateinit var mViewModel: AuthorizationViewModel
    private lateinit var mBinding: AuthorizationFragmentBinding
    private lateinit var mGoogleLauncher: GoogleLauncher
    private lateinit var mSignInIntent: Intent
    private var mGoogleSignInLogic: GoogleSignInLogic? = GoogleSignInLogic()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSignInIntent = mGoogleSignInLogic?.buildGoogleClient(requireActivity())!!

        mGoogleLauncher = GoogleLauncher(
            requireActivity().activityResultRegistry
        )

        lifecycle.addObserver(mGoogleLauncher)
    }

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
                ).observe(viewLifecycleOwner) { user -> checkUserRole(user) }
            } else {
                Toast.makeText(context, "Incorrect data!",Toast.LENGTH_SHORT).show()
            }
        }

        mBinding.btnAuthGoogleBooks.setOnClickListener {
            mGoogleLauncher.launchGoogleSignIn(mSignInIntent)
        }

        mViewModel = ViewModelProvider(this)[AuthorizationViewModel::class.java]

        return mBinding.root
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

            view?.findNavController()?.navigate(
                AuthorizationFragmentDirections.actionAuthToMyAcc()
            )
        } else {
            Toast.makeText(context, "Incorrect user",Toast.LENGTH_SHORT).show()
        }
    }

    private fun onResultListener(intent: Intent?) {
        if (intent == null)
            return

        val userInfo = mGoogleSignInLogic!!.handleSignInResult(intent)

        if (userInfo != null) {
            checkUserRole(
                User(
                    login = userInfo[0],
                    name = userInfo[1],
                    lastName = userInfo[2],
                    role = Role.User
                )
            )
        } else {
            Toast.makeText(context, "Incorrect Google user",Toast.LENGTH_SHORT).show()
        }

    }

    inner class GoogleLauncher (
        private val registry : ActivityResultRegistry
        ): DefaultLifecycleObserver {
        lateinit var getContent : ActivityResultLauncher<Intent>

        override fun onCreate(owner: LifecycleOwner) {
            getContent = registry.register(
                "Text_Bestolkoviy",
                owner,
                ActivityResultContracts.StartActivityForResult()
            ) {
                onResultListener(it.data)
            }
        }

        fun launchGoogleSignIn(intent: Intent) {
            getContent.launch(intent)
        }
    }

}