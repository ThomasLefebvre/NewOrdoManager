package com.lefebvre.thomas.newordomanager.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.lefebvre.thomas.newordomanager.R
import com.lefebvre.thomas.newordomanager.databinding.FragmentMainBinding


class FragmentMain : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.floatingButtonAdd.setOnClickListener {
            fragmentMainToAddFragment()
        }


        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)


        return binding.root
    }

    private fun fragmentMainToAddFragment() {
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_addFragment)
    }

}
