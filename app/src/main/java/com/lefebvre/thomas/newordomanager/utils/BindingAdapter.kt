package com.lefebvre.thomas.newordomanager.utils


import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lefebvre.thomas.newordomanager.R
import fr.thomas.lefebvre.toutougo.utils.setDateToString
import java.text.SimpleDateFormat
import java.util.*



@BindingAdapter("date")
fun TextView.setDateSting(date:Long){
    if(date>0){
        text = setDateToString(date)
    }

}









