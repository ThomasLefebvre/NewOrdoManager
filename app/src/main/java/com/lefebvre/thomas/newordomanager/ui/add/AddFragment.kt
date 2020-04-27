package com.lefebvre.thomas.newordomanager.ui.add

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar

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


    private lateinit var binding: FragmentAddBinding
    private var cal = Calendar.getInstance()
    private var calEnd = Calendar.getInstance()
    private var year = 1
    private var month = 1
    private var day = 1

    private val listInt = ArrayList<String>()
    private lateinit var viewModel: AddOrdoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        viewModel = ViewModelProvider(this).get(AddOrdoViewModel::class.java)
        binding.viewModel = viewModel

        onClickDatePicker()

        for (i in 1..10) {
            listInt.add(i.toString())
        }

        setSpinnerInt()

        clickSaveButton()

        checkedRadioButton()





        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Observe select duration
        viewModel.durationInt.observe(viewLifecycleOwner, androidx.lifecycle.Observer { duration ->
            if (viewModel.dateStartLong.value != null) {//init date end if date start is select
                viewModel.dateEndLong.value = setDateEnd()
                calEnd.clear()

            }
        })

        //Observe select date
        viewModel.dateStartLong.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { dateStart ->
                if (viewModel.durationInt.value != null) {//init date end if duration is select
                    viewModel.dateEndLong.value = setDateEnd()
                    calEnd.clear()
                }
            })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        setSpinnerInt()
        super.onResume()
    }


    private fun clickSaveButton() {
        binding.floatingButtonSave.setOnClickListener {

            viewModel.insertOrdo()
            view!!.findNavController().navigate(R.id.action_addFragment_to_mainFragment)

            Toast.makeText(requireContext(),viewModel.name.value.toString()+viewModel.firstName.value+viewModel.dateEndLong.value.toString()+viewModel.dateStartLong.value.toString(),Toast.LENGTH_LONG).show()
        }
    }


    // SET DATE END IF DATE START AND DURATION IS SELECT
    private fun setDateEnd(): Long {
        calEnd.set(Calendar.YEAR, year)
        calEnd.set(Calendar.MONTH, month)
        calEnd.set(Calendar.DAY_OF_MONTH, day)

        val durationInt = viewModel.durationInt.value!!.toInt()
        if (binding.radioButtonDay.isChecked) {
            calEnd.add(Calendar.DAY_OF_MONTH, durationInt)
        } else if (binding.radioButtonWeek.isChecked) {
            calEnd.add(Calendar.WEEK_OF_MONTH, durationInt)
        } else calEnd.add(Calendar.MONTH, durationInt)

        return calEnd.timeInMillis

    }

    // LISTENER RADIO GROUP BUTTON
    private fun checkedRadioButton() {

        //init date end if date start and duration is select

        binding.radioButtonDay.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (viewModel.dateStartLong.value != null && viewModel.durationInt.value != null) {
                viewModel.dateEndLong.value = setDateEnd()
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.alert_msg_no_date),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        })
        binding.radioButtonMonth.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (viewModel.dateStartLong.value != null && viewModel.durationInt.value != null) {
                viewModel.dateEndLong.value = setDateEnd()
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.alert_msg_no_date),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        })
        binding.radioButtonWeek.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (viewModel.dateStartLong.value != null && viewModel.durationInt.value != null) {
                viewModel.dateEndLong.value = setDateEnd()
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.alert_msg_no_date),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        })

    }


    // DATE PICKER

    private fun onClickDatePicker() {

        val dateListener =//init date picker
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                this.year = year
                this.month = monthOfYear
                this.day = dayOfMonth


                viewModel.dateStartLong.value = cal.timeInMillis


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
        binding.filledExposedDropdownDuration.setAdapter(adapter)

    }

}
