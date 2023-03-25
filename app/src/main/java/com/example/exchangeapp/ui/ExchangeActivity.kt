package com.example.exchangeapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeapp.R
import com.example.exchangeapp.utill.Constants
import kotlinx.android.synthetic.main.activity_main.*

class ExchangeActivity : AppCompatActivity() ,View.OnClickListener , TextView.OnEditorActionListener {

    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("USER" , Context.MODE_PRIVATE)
        setContentView(R.layout.activity_main)
        btn_equal.setOnClickListener(this)
        et_first_currency.setOnEditorActionListener(this)
        ic_logout.setOnClickListener(this)
        et_second_currency.setOnEditorActionListener(this)
        val a : String
    }

    override fun onClick(view: View?) {
        when(view){
            btn_equal ->{
                convertAndPut()
            }
            ic_logout ->{
             sharedPref.edit().clear().apply()
             startActivity(Intent(this@ExchangeActivity , LoginActivity ::class.java))
                finish()
            }
        }
    }

    override fun onEditorAction(p0: TextView?, actionId: Int, event: KeyEvent?): Boolean {
      if(actionId == EditorInfo.IME_ACTION_DONE){
            convertAndPut()
      }
        return false

    }


    fun convertAndPut(){
        if(et_first_currency.text.toString().isNotEmpty()){
            val currencyValue = et_first_currency.text.toString().toFloat() * Constants.RATEUSD
            et_second_currency.setText(currencyValue.toString())

            val length  = et_first_currency.length()
            et_first_currency.text.delete(0, length)


        }else if (et_second_currency.text.toString().isNotEmpty()){
            val currencyValue = et_second_currency.text.toString().toFloat() * Constants.RATEIRN
            et_first_currency.setText(currencyValue.toString())

            val length  = et_second_currency.length()
            et_second_currency.text.delete(length - 1, length)
        }
    }
}

