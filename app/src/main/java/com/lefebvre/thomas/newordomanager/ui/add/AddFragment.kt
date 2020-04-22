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

import com.lefebvre.thomas.newordomanager.R
import com.lefebvre.thomas.newordomanager.databinding.FragmentAddBinding
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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false)

        onClickDatePicker()
        for (i in 0..10){
            listInt.add(i.toString())
        }
        setSpinnerInt()

        return binding.root
    }


        // DATE PICKER

    private fun onClickDatePicker() {

        val dateListener =//init date picker
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

//                viewModel.dateEvent.value = (cal.timeInMillis)//get date pick
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
