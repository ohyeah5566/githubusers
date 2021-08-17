package com.example.githubusers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.githubusers.databinding.FragmentMineBinding
import dagger.hilt.android.AndroidEntryPoint

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
            Log.d("MineFragment", user.toString())
            //TODO ui
        })
        viewModel.loadSpecUser("ohyeah5566")
        return binding.root
    }
}