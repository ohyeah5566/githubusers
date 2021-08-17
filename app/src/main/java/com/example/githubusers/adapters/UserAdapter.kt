package com.example.githubusers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.githubusers.data.GithubUser
import com.example.githubusers.databinding.ItemUserBinding

class UserAdapter :
    PagingDataAdapter<GithubUser, UserAdapter.ViewHolder>(GithubUserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: GithubUser) {
            binding.ivAvatar.load(user.avatar_url) {
                transformations(CircleCropTransformation())
            }
            binding.tvName.text = user.login
            binding.tvType.text = user.type
        }
    }
}

class GithubUserDiffCallback : DiffUtil.ItemCallback<GithubUser>() {
    override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem == newItem
    }

}