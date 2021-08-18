package com.example.githubusers

import android.content.Intent
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment :Fragment() {
    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserListBinding.inflate(layoutInflater)
        binding.listview.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))

        val adapter = UserAdapter()
        binding.listview.adapter = adapter
        adapter.onUserClickEvent = { user ->
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(UserDetailActivity.INTENT_KEY_USER, user)
            startActivity(intent)
        }

        viewModel.users.observe(viewLifecycleOwner, { data ->
            adapter.submitData(lifecycle, data)
        })

        viewModel.loadUsers()
        return binding.root
    }
}