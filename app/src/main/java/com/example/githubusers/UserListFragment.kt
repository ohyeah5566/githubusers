package com.example.githubusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.adapters.UserAdapter
import com.example.githubusers.databinding.FragmentUserListBinding

class UserListFragment :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserListBinding.inflate(layoutInflater)
        binding.listview.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.HORIZONTAL))
        binding.listview.adapter = UserAdapter(emptyList())

        return binding.root
    }
}