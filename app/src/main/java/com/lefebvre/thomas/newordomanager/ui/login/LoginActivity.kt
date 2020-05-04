package com.lefebvre.thomas.newordomanager.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.lefebvre.thomas.newordomanager.MainActivity
import com.lefebvre.thomas.newordomanager.R
import com.lefebvre.thomas.newordomanager.database.helper.UserHelper

class LoginActivity : AppCompatActivity() {

    lateinit var listProvider: List<AuthUI.IdpConfig>
    private val userHelper = UserHelper()
    private val CODE_LOGIN: Int = 123
    private val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Log.d("DEBUG","Login activity")

        listProvider = arrayListOf(//create list of method connexion
            AuthUI.IdpConfig.EmailBuilder().build(),//email connexion
            AuthUI.IdpConfig.GoogleBuilder().build()//google connexion
        )
        if (currentUser== null) {
            signInOptions()
        }
        else{
            launchActivity(currentUser)
        }
    }

    private fun signInOptions() {
        Log.d("LOGIN", "Current user is nul")
       startActivityForResult(
           AuthUI.getInstance()
               .createSignInIntentBuilder()
               .setAvailableProviders(listProvider)
               .build(),

           CODE_LOGIN
       )
    }

// --- CREATE USER TO DATABASE IF NOT ALREADY EXIST ---

    private fun launchActivity(currentUser: FirebaseUser?) {
       startActivity(Intent(this,MainActivity::class.java))
        finish()
    }


// --- ACTIVITY LOGIN RESULT---

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {//result of login activity
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODE_LOGIN -> if (resultCode == Activity.RESULT_OK) {
                Log.d("LOGIN", "Result OK")
                launchActivity(FirebaseAuth.getInstance().currentUser)

            } else {
                finish()
            }
        }
    }
}




