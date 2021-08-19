package com.example.githubusers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.githubusers.databinding.FragmentMineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MineFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMineBinding.inflate(layoutInflater)
        viewModel.user.observe(viewLifecycleOwner, { user ->
            binding.ivAvatar.load(user.avatar_url) {
                transformations(CircleCropTransformation())
            }
            binding.tvFollower.text = getString(R.string.mine_follower, user.followers)
            binding.tvFollowing.text = getString(R.string.mine_following, user.following)
            binding.tvLogin.text = user.login
            binding.tvName.text = user.name
            binding.tvMail.text = user.email
        })

        lifecycleScope.launchWhenCreated {
            viewModel.userFail.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        if (savedInstanceState == null) {
            viewModel.loadSpecUser("ohyeah5566")
        }

        return binding.root
    }
}