package com.example.exchangeapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeapp.MainActivity
import com.example.exchangeapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , View.OnClickListener {

   lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         sharedPref = getSharedPreferences("USER" , Context.MODE_PRIVATE)
         val userName = sharedPref.getString("USERNAME" , "") ?: ""
        if(userName.isNotEmpty()){
            startActivity(Intent(this@LoginActivity , ExchangeActivity::class.java ))
            finish()

        }

        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener(this)



    }

    override fun onClick(view: View?) {
        when(view){
            btn_login ->{
                val userNameValue = et_user_name.text.toString()
                Log.d("username" , userNameValue)
                if(!userNameValue.isNullOrEmpty()){
                    sharedPref.edit()?.putString("USERNAME" , userNameValue)?.apply()
                    startActivity(Intent(this@LoginActivity , ExchangeActivity::class.java ))
                    finish()

                }
            }

        }


    }
}