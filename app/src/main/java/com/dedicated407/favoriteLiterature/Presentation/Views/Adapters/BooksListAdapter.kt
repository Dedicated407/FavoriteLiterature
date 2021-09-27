package com.dedicated407.favoriteLiterature.Presentation.Views.Adapters

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.annotation.NonNull
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.databinding.ListBooksItemFragmentBinding
import org.jetbrains.annotations.NotNull
import java.lang.Exception


class BooksListAdapter(private var books: List<Book>) :
    RecyclerView.Adapter<BooksListAdapter.BookViewHolder>() {

    @NonNull
    @NotNull
    override fun onCreateViewHolder(
        @NonNull
        @NotNull
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val binding: ListBooksItemFragmentBinding =
            ListBooksItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int,
    ) {
        try {
            holder.binding.bookImage.setImageBitmap(
                BitmapFactory.decodeFileDescriptor(
                    holder.binding.bookImage.context.contentResolver.openFileDescriptor(
                        Uri.parse(books[position].images?.get(0)), "r"
                    )?.fileDescriptor
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.binding.itemBookName.text = books[position].name
        holder.binding.itemAuthorName.text = books[position].author.toString()
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun getData(): List<Book> {
        return books
    }

    class BookViewHolder(var binding: ListBooksItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) { }
}