package com.example.paging3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class FooterAdapter(private val retry: () -> Unit) : LoadStateAdapter<FooterAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(retry, loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.footer_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        private val retryButton: Button = itemView.findViewById(R.id.retry_button)
        private val txtErrorMessage: TextView = itemView.findViewById(R.id.txt_error_msg)

        fun bind(retry: () -> Unit, loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error

            if (loadState is LoadState.Error) {
                txtErrorMessage.visibility = View.VISIBLE
            } else {
                txtErrorMessage.visibility = View.GONE
            }
            retryButton.setOnClickListener {
                retry()
            }
        }
    }
}