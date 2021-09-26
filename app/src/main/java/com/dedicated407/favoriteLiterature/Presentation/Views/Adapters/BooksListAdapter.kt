package com.dedicated407.favoriteLiterature.Presentation.Views.Adapters

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.NonNull
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.databinding.ListBooksItemFragmentBinding
import org.jetbrains.annotations.NotNull


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
        position: Int
    ) {
//        if (books[position].images == null)
//            holder.binding.bookImage.visibility = View.GONE
        holder.binding.bookImage.setImageURI(Uri.parse(books[position].images?.get(0)))
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