package com.example.learn_android

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

//Todo change android sdks and gradle directories
class MainActivity : AppCompatActivity() {

    private var TInput: TextView? = null
    var lastdot = false
    var lastnum = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TInput = findViewById(R.id.TInput)
    }

    fun onDigit(view: View){
        TInput?.append((view as Button).text)
        lastnum=true
        lastdot=false
    }

    fun onClear(view: View){
        TInput?.text = ""
    }

    fun onDot(view: View){
        if(lastnum and !lastdot){
            TInput?.append(".")
            lastnum=false
            lastdot=true
        }
    }

    private fun isOperatoradded(value: String): Boolean {
        return if(value.startsWith("-")){
            false
        } else{
            value.contains("+")||
            value.contains("*")||
            value.contains("/")||
            value.contains("-")
        }
    }

    fun onOperator(view: View){
        TInput?.text?.let {

            if (lastnum && !isOperatoradded(it.toString())){
                TInput?.append((view as Button).text)
                lastnum=false
                lastdot=false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun onequal(view: View){
        if (lastnum){
            var input = TInput?.text.toString()
            var prefix = ""
            try {
                if (input.startsWith("-")){
                    prefix = "-"
                    input = input.substring(1)

                }
                if (input.contains("-")){
                    val splitVal=input.split("-")
                    var one = splitVal[0]
                    var two = splitVal[1]

                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    TInput?.text =removeDot((one.toDouble()- two.toDouble()).toString())
                }else if (input.contains("+")){
                    val splitVal=input.split("+")
                    var one = splitVal[0]
                    var two = splitVal[1]

                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    TInput?.text =removeDot((one.toDouble()+ two.toDouble()).toString())
                }else if (input.contains("/")){
                    val splitVal=input.split("/")
                    var one = splitVal[0]
                    var two = splitVal[1]

                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    TInput?.text =removeDot((one.toDouble()/ two.toDouble()).toString())
                }else if (input.contains("*")){
                    val splitVal=input.split("*")
                    var one = splitVal[0]
                    var two = splitVal[1]

                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    TInput?.text =removeDot((one.toDouble()* two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeDot(result:String): String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length-2)
        return value
    }
}