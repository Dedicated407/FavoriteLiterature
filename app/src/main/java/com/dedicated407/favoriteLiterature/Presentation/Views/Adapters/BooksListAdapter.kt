package com.dedicated407.favoriteLiterature.Presentation.Views.Adapters

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookListViewDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.toBitmap
import com.dedicated407.favoriteLiterature.Presentation.Views.BooksListFragmentDirections
import com.dedicated407.favoriteLiterature.databinding.ListBooksItemFragmentBinding
import org.jetbrains.annotations.NotNull
import java.lang.Exception


class BooksListAdapter(private var books: List<BookListViewDTO>) :
    RecyclerView.Adapter<BooksListAdapter.BookViewHolder>() {

    @NonNull
    @NotNull
    override fun onCreateViewHolder(
        @NonNull
        @NotNull
        parent: ViewGroup,
        viewType: Int,
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

        holder.binding.cardViewList.setOnClickListener { v ->
            v.findNavController().navigate(
                BooksListFragmentDirections.actionListBooksToBookInfo(books[position].id.toString())
            )
        }

        val book = books[position]

        try {
            holder.binding.bookImage.setImageBitmap(
                Uri.parse(book.images[0]).toBitmap(
                    holder.itemView.context,
                    holder.binding.bookImage.layoutParams.width,
                    holder.binding.bookImage.layoutParams.height,
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.binding.itemBookName.text = book.name
        holder.binding.itemAuthorName.text = book.authorName
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun getData(): List<BookListViewDTO> {
        return books
    }

    class BookViewHolder(var binding: ListBooksItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)
}