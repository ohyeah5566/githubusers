package com.example.githubusers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.githubusers.data.GithubUser
import com.example.githubusers.databinding.ItemUserBinding

class UserAdapter(val list: List<GithubUser>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

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
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size


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