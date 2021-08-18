package com.example.githubusers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.example.githubusers.data.GithubUser
import com.example.githubusers.databinding.ActivityUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            intent.getParcelableExtra<GithubUser>(INTENT_KEY_USER)?.let { user ->
                showUserData(user)
                viewModel.loadSpecUser(user.login)
            }
        }

        viewModel.user.observe(this, { user ->
            showUserData(user)
        })
    }

    private fun showUserData(user: GithubUser) {
        binding.ivAvatar.load(user.avatar_url) {
            transformations(CircleCropTransformation())
        }
        binding.tvName.text = user.login
        binding.tvLocation.text = user.location ?: ""
        binding.tvProfileUrl.text = user.html_url
    }

    companion object {
        val INTENT_KEY_USER = "INTENT_KEY_USER_NAME"
    }
}