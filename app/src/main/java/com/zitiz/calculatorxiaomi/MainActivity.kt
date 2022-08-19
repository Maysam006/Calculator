package com.zitiz.calculatorxiaomi

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.zitiz.calculatorxiaomi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Error
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var operator :Char = '+'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNUmberClicked()
        onOperatorClicked()


    }

    private fun onOperatorClicked() {


        binding.btnMosbat.setOnClickListener {
           if (binding.txtExpression.text.isNotEmpty()){
                val mychar = binding.txtExpression.text.last()
                if (mychar != '+' && mychar != '-' && mychar != '*' && mychar != '/') {
                    appendText("+")
                } else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.btnManfi.setOnClickListener {
           if (binding.txtExpression.text.isNotEmpty()){
                val mychar = binding.txtExpression.text.last()
                if (mychar != '+' && mychar != '-' && mychar != '*' && mychar != '/') {
                    appendText("-")
                } else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnZarb.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()){
                val mychar = binding.txtExpression.text.last()
                if (mychar != '+' && mychar != '-' && mychar != '*' && mychar != '/') {
                    appendText("*")
                } else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }


        }

        binding.btntaghsaim.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()){

                val mychar = binding.txtExpression.text.last()
                if (mychar != '+' && mychar != '-' && mychar != '*' && mychar != '/') {
                    appendText("/")
                } else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnparantezbaz.setOnClickListener {
            appendText("(")
        }

        binding.btnparantezbaste.setOnClickListener {
            appendText(")")
        }

        binding.btnAC.setOnClickListener {
            binding.txtExpression.text = " "
            binding.txtJavab.text = " "
        }
        binding.btnPakkardan.setOnClickListener {

            val oldtext = binding.txtExpression.text.toString()

            if (oldtext.isNotEmpty())
                binding.txtExpression.text = oldtext.substring(0, oldtext.length - 1)

        }

    }

    private fun onNUmberClicked() {

        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty())
                appendText("0")
        }

        binding.btn1.setOnClickListener {
            appendText("1")
        }

        binding.btn2.setOnClickListener {
            appendText("2")
        }


        binding.btn3.setOnClickListener {
            appendText("3")
        }

        binding.btn4.setOnClickListener {
            appendText("4")
        }


        binding.btn5.setOnClickListener {
            appendText("5")
        }


        binding.btn6.setOnClickListener {
            appendText("6")
        }


        binding.btn7.setOnClickListener {
            appendText("7")
        }

        binding.btn8.setOnClickListener {
            appendText("8")
        }

        binding.btn9.setOnClickListener {
            appendText("9")
        }

        binding.btnDot.setOnClickListener {
            if (binding.txtExpression.text.isEmpty() || binding.txtJavab.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.txtExpression.text.contains(".")) {
                appendText(".")
            } else if (binding.txtExpression.text.contains("+")) {
                appendText(".")

            } else if (binding.txtExpression.text.contains("-")) {
                appendText(".")

            } else if (binding.txtExpression.text.contains("*")) {
                appendText(".")

            } else if (binding.txtExpression.text.contains("/")) {
                appendText(".")

            } else if (binding.txtExpression.text.contains("(")) {
                appendText(".")

            }
        }
        binding.btnMosavi.setOnClickListener {

            try {


                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()

                val longresult = result.toLong()
                //15 == 15.0
                if (result == longresult.toDouble()) {
                    binding.txtJavab.text = longresult.toString()
                } else {
                    binding.txtJavab.text = result.toString()
                }
            }catch (e :Exception){
                binding.txtJavab.text =" "
                binding.txtExpression.text =" "

                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun appendText(newtext: String) {

        if (binding.txtJavab.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }

        binding.txtJavab.text = ""

        binding.txtExpression.append(newtext)

        val viewtree: ViewTreeObserver = binding.HorizontalScrollViewTxtExpression.viewTreeObserver
        viewtree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.HorizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.HorizontalScrollViewTxtExpression.scrollTo(binding.txtExpression.width , 0)
            }
        })
    }
}










