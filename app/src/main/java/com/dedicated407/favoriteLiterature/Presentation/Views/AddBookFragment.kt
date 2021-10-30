package com.dedicated407.favoriteLiterature.Presentation.Views

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.OpenDocument
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.AddBookViewModel
import com.dedicated407.favoriteLiterature.databinding.AddBookFragmentBinding

class AddBookFragment : Fragment() {
    private lateinit var mViewModel: AddBookViewModel
    private lateinit var mBinding: AddBookFragmentBinding
    private val images: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = AddBookFragmentBinding.inflate(layoutInflater, container, false)

        mBinding.btnUploadImage.setOnClickListener {
            requireActivity().activityResultRegistry.register("registerImage", OpenDocument())
            { result ->
                if (result != null) {
                    requireActivity().applicationContext.contentResolver
                        .takePersistableUriPermission(
                            result,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                    mViewModel.setImage(result.toString())
                    images.add(images.size, mViewModel.image.value!!)
                }
            }.launch(arrayOf("image/*"))
        }

        mBinding.btnAddBook.setOnClickListener { v ->
            if (mBinding.inputBookName.text.toString().isNotEmpty() && images.size != 0) {
                mViewModel.addBook(
                    Book(
                        name = mBinding.inputBookName.text.toString(),
                        author = User(
                            name = mBinding.inputAuthorName.text.toString(),
                            role = User.Role.Writer
                        ),
                        description = mBinding.inputBookDescription.text.toString(),
                        images = images
                    )
                )
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()

                mBinding.apply {
                    bookImage.visibility = View.GONE
                    images.clear()
                    inputBookName.text = null
                    inputAuthorName.text = null
                    inputBookDescription.text = null
                }
            } else {
                Toast.makeText(context, "You have not entered all the data", Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel = ViewModelProvider(this)[AddBookViewModel::class.java]

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(AddBookViewModel::class.java)
        mViewModel.image.observe(viewLifecycleOwner, {
            if (it == null) {
                mBinding.bookImage.setImageDrawable(null)
                mBinding.bookImage.visibility = View.GONE
            } else {
                val uri = Uri.parse(mViewModel.image.value)
                mBinding.bookImage.setImageBitmap(
                    BitmapFactory.decodeFileDescriptor(
                        requireContext().contentResolver.openFileDescriptor(
                            uri, "r")?.fileDescriptor
                    )
                )
                mBinding.bookImage.visibility = View.VISIBLE
            }
        })
    }
}