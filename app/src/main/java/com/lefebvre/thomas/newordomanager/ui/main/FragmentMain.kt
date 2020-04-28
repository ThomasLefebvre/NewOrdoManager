package com.lefebvre.thomas.newordomanager.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.chip.ChipGroup

import com.lefebvre.thomas.newordomanager.R
import com.lefebvre.thomas.newordomanager.databinding.FragmentMainBinding
import com.lefebvre.thomas.newordomanager.ui.add.AddOrdoViewModel


class FragmentMain : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var adapterOrdo: OrdoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.floatingButtonAdd.setOnClickListener {
            fragmentMainToAddFragment()
        }


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        adapterOrdo = OrdoAdapter(OrdoListener { ordo ->
            clickOrdo()
        })

        binding.recyclerView.adapter = adapterOrdo

        viewModel.listOrdo.observe(viewLifecycleOwner, Observer { ordo ->

            adapterOrdo.submitList(ordo)

            if (viewModel.listOrdo.value == null) {
                binding.textViewNoOrdo.visibility = View.VISIBLE
            }
        })

        viewModel.getAllOrdoByName()

        checkChipFilter()

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun clickOrdo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun fragmentMainToAddFragment() {
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_addFragment)
    }


    private fun checkChipFilter() {
        binding.chipGroup.setOnCheckedChangeListener(ChipGroup.OnCheckedChangeListener() { chipGroup: ChipGroup, i: Int ->
            when (i) {
                R.id.chipName -> {
                    viewModel.getAllOrdoByName()
                    adapterOrdo.notifyDataSetChanged()
                }
                R.id.chipDateEnd -> {
                    viewModel.getAllOrdoByDateEnd()
                    adapterOrdo.notifyDataSetChanged()
                }
            }
        })
    }

}
