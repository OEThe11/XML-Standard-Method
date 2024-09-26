package com.example.xmlstandardmethod.screens.github.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xmlstandardmethod.databinding.ItemContainerBinding
import com.example.xmlstandardmethod.models.network.GitHubRepo

class GitHubPagingAdapter: PagingDataAdapter<GitHubRepo, GitHubViewHolder>(GitHubRepoDiffCallback()) {
    override fun onBindViewHolder(holder: GitHubViewHolder, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.bind(repo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubViewHolder {
        val binding = ItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitHubViewHolder(binding)
    }

    class GitHubRepoDiffCallback : DiffUtil.ItemCallback<GitHubRepo>() {

        override fun areItemsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
            // Check if items are the same based on their unique ID (the repo id)
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
            // Check if the contents of the items are the same
            return oldItem == newItem
        }
    }
}

class GitHubViewHolder(private val binding: ItemContainerBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(repo: GitHubRepo){
            binding.apply {
                repoName.text = repo.name
                repoDescription.text = repo.description ?: "No description Available"
                repoStars.text = repo.stargazersCount.toString()
                repoForks.text = repo.forksCount.toString()
                Glide.with(itemView.context)
                    .load(repo.owner.avatarUrl)
                    .into(ownerAvatar)
            }
        }
    }