package com.dedicated407.favoriteLiterature.Presentation.Views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.OpenDocument
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.AddBookViewModel
import com.dedicated407.favoriteLiterature.R
import com.dedicated407.favoriteLiterature.databinding.AddBookFragmentBinding

class AddBookFragment : Fragment() {
    private var mViewModel: AddBookViewModel? = null
    private var mBinding: AddBookFragmentBinding? = null
    private val images: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = AddBookFragmentBinding.inflate(layoutInflater, container, false)

        mBinding!!.btnUploadImage.setOnClickListener {
            activity?.activityResultRegistry?.register("key", OpenDocument()) { result ->
                activity?.applicationContext?.contentResolver?.takePersistableUriPermission(
                    result,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                images.add(images.size, result.toString())
            }
            getContent.launch("image/*")
        }

        mBinding!!.btnShowAllBooks.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.action_addBook_to_listBooks)
        }

        mBinding!!.btnAddBook.setOnClickListener { v ->
            if (mBinding!!.inputBookName.text.toString().isNotEmpty() && images.size != 0) {
                mViewModel!!.addBook(
                    BookDTO(
                        Book(
                            mBinding!!.inputBookName.text.toString(),
                            User(mBinding!!.inputAuthorName.text.toString(), ""),
                            mBinding!!.inputBookDescription.text.toString(),
                            images
                        )
                    )
                )
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(v).navigate(R.id.action_addBook_to_listBooks)
            } else {
                Toast.makeText(context, "You have not entered all the data", Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel = ViewModelProvider(this)[AddBookViewModel::class.java]

        return mBinding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
        mViewModel = null
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        mBinding?.bookImage?.setImageURI(uri)
        images.add(uri.toString())
    }
}