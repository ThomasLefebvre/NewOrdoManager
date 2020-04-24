package com.lefebvre.thomas.newordomanager.ui.add

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

import com.lefebvre.thomas.newordomanager.R
import com.lefebvre.thomas.newordomanager.databinding.FragmentAddBinding
import com.lefebvre.thomas.newordomanager.ui.main.MainViewModel
import fr.thomas.lefebvre.toutougo.utils.setDateToString
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {


    private lateinit var binding:FragmentAddBinding
    private var cal = Calendar.getInstance()
    private val listInt=ArrayList<String>()
    private lateinit var viewModel:MainViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false)

        viewModel=ViewModelProvider(activity!!).get(MainViewModel::class.java)
        binding.viewModel=viewModel

        onClickDatePicker()
        for (i in 1..10){
            listInt.add(i.toString())
        }
        setSpinnerInt()

        binding.lifecycleOwner = this

        return binding.root
    }


        // DATE PICKER

    private fun onClickDatePicker() {

        val dateListener =//init date picker
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)


                viewModel.dateStartLong.value= cal.timeInMillis


            }

        binding.buttonDateStart.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setSpinnerInt() {//init spinner for max customer

        val adapter =
            ArrayAdapter<String>(requireContext(), R.layout.dropdown_menu, listInt)
        binding.filledExposedDropdownMaxCustomer.setAdapter(adapter)

    }

}
