package com.lefebvre.thomas.newordomanager.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.ChipGroup

import com.lefebvre.thomas.newordomanager.R
import com.lefebvre.thomas.newordomanager.databinding.FragmentMainBinding
import com.lefebvre.thomas.newordomanager.ui.add.AddOrdoViewModel
import kotlinx.android.synthetic.main.fragment_main.*


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

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapterOrdo

        viewModel.listOrdo.observe(viewLifecycleOwner, Observer { ordo ->

            adapterOrdo.submitList(ordo)

            if (viewModel.listOrdo.value == null) {
                binding.textViewNoOrdo.visibility = View.VISIBLE
            }
        })

        viewModel.getAllOrdoByName()

        checkChipFilter()



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchName.value = query
                viewModel.getOrdoByNameQuery()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                Toast.makeText(requireContext(),query,Toast.LENGTH_LONG).show()
                return true
            }

        })








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

                }
                R.id.chipDateEnd -> {
                    viewModel.getAllOrdoByDateEnd()

                }
            }
        })
    }

}
