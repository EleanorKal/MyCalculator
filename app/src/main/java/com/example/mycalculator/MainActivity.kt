package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    // val tvInput_ = findViewById(R.id.tvInput) as TextView   /* Kotlin hates global variables - keep them local */
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onDigit(view: View) {
        // Toast.makeText(this, "Button works", Toast.LENGTH_SHORT).show()
        val tvInput_ = findViewById(R.id.tvInput) as TextView
        tvInput_.append((view as Button).text)
        lastNumeric = true

    }

    fun onClear(view: View) {
        val tvInput_ = findViewById(R.id.tvInput) as TextView
        tvInput_.text = ""
        lastNumeric = false
        lastDot = false

    }

    fun onDecimalPoint(view: View) {
        val tvInput_ = findViewById(R.id.tvInput) as TextView
        if (lastNumeric && !lastDot) {
            tvInput_.append(("."))
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        val tvInput_ = findViewById(R.id.tvInput) as TextView
        if (lastNumeric) {
            var tvValue = tvInput_.text.toString()
            var prefix = ""
            // as the equals button finalizes the calculation. We want to build a try exception handler here
            try{

                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1) // returns a substring of a string that starts at the specific startindex - in this case from pos 2, [1]
                    // the above startWith if statement aims to disregard subsequent minuses after the first minus is inputted
                }

                // my code
                else if (tvValue.startsWith("/")) {
                   {
                       prefix = "/"
                       tvValue = tvValue.substring(1)

                   }
                }

                else if (tvValue.startsWith("+")) {
                    {
                        prefix = "+"
                        tvValue = tvValue.substring(1)

                    }
                }

                else if  (tvValue.startsWith("+")) {
                    {
                        prefix = "+"
                        tvValue = tvValue.substring(1)

                    }
                }


               // end of my code

                if(tvValue.contains("-")){
                    // String splitter
                    val splitValue = tvValue.split("-")
                    // var one represents the first value of the split at pos. zero
                    var one = splitValue[0]
                    var two = splitValue[1]

                    //Nested if statement as a workaround for the Parent if statement
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    // when performing the calc this is calculated toDouble
                    // and then the output is toString as we're displaying on a textView
                    tvInput_.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }
                // my code
                else if(tvValue.contains("*")){
                    // String splitter
                    val splitValue = tvValue.split("*")
                    // var one represents the first value of the split at pos. zero
                    var one = splitValue[0]
                    var two = splitValue[1]

                    //Nested if statement as a workaround for the Parent if statement
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    // when performing the calc this is calculated toDouble
                    // and then the output is toString as we're displaying on a textView
                    tvInput_.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }

                else if(tvValue.contains("+")){
                    // String splitter
                    val splitValue = tvValue.split("+")
                    // var one represents the first value of the split at pos. zero
                    var one = splitValue[0]
                    var two = splitValue[1]

                    //Nested if statement as a workaround for the Parent if statement
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    // when performing the calc this is calculated toDouble
                    // and then the output is toString as we're displaying on a textView
                    tvInput_.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }

                else if(tvValue.contains("/")){
                    // String splitter
                    val splitValue = tvValue.split("/")
                    // var one represents the first value of the split at pos. zero
                    var one = splitValue[0]
                    var two = splitValue[1]

                    //Nested if statement as a workaround for the Parent if statement
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    // when performing the calc this is calculated toDouble
                    // and then the output is toString as we're displaying on a textView
                    tvInput_.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }
                // end of my code


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }


    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length - 2)
            return value
        }
        TODO() // add return value...
        // this function will then be used in the splitter above
    }


    // the function is saying that if the final number has been inputted but none of the calculator operators such as +
    // +, -, / , * have been added then set the final number to false - disabling the need to enter more numbers
    // In addition set the decimal - lastDot to false, so that no further decimals can be added
    // depoly this function to the operator buttons
    fun onOperator(view: View) {
        val tvInput_ = findViewById(R.id.tvInput) as TextView
        if(lastNumeric && !isOperatorAdded(tvInput_.text.toString())) {
            tvInput_.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        // For example if I input -65 this - should not limit me from adding another operator e.g. * or -
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+")
                    || value.contains("-")
        }
        // the above if statement ensures that only a single calculation operator can be deployed
    }



}