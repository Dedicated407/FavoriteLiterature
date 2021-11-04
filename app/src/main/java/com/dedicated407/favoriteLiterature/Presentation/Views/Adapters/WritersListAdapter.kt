package com.dedicated407.favoriteLiterature.Presentation.Views.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.dedicated407.favoriteLiterature.Domain.Model.Writer
import com.dedicated407.favoriteLiterature.databinding.ListItemFragmentBinding
import org.jetbrains.annotations.NotNull

class WritersListAdapter (private var writers: List<Writer>) :
    RecyclerView.Adapter<WritersListAdapter.WriterViewHolder>() {

    @NonNull
    @NotNull
    override fun onCreateViewHolder(
        @NonNull
        @NotNull
        parent: ViewGroup,
        viewType: Int,
    ): WriterViewHolder {
        val binding: ListItemFragmentBinding =
            ListItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return WriterViewHolder (binding)
    }

    override fun onBindViewHolder(
        holder: WriterViewHolder,
        position: Int,
    ) {
        holder.binding.textViewName.text = writers[position].name
    }

    override fun getItemCount(): Int {
        return writers.size
    }

    fun getWriters(): List<Writer> {
        return writers
    }

    class WriterViewHolder(var binding: ListItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) { }
}