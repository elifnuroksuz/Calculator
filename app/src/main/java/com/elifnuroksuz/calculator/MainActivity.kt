package com.elifnuroksuz.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val ADDITION = '+'
    private val SUBTRACTION = '-'
    private val MULTIPLICATION = '*'
    private val DIVISION = '/'
    private val PERCENT = '%'

    private var currentSymbol: Char = '0'

    private var firstValue: Double = Double.NaN
    private var secondValue: Double = Double.NaN
    private lateinit var inputDisplay: TextView
    private lateinit var outputDisplay: TextView
    private lateinit var decimalFormat: DecimalFormat
    private lateinit var button0: MaterialButton
    private lateinit var button1: MaterialButton
    private lateinit var button2: MaterialButton
    private lateinit var button3: MaterialButton
    private lateinit var button4: MaterialButton
    private lateinit var button5: MaterialButton
    private lateinit var button6: MaterialButton
    private lateinit var button7: MaterialButton
    private lateinit var button8: MaterialButton
    private lateinit var button9: MaterialButton
    private lateinit var buttonDot: MaterialButton
    private lateinit var buttonAdd: MaterialButton
    private lateinit var buttonSub: MaterialButton
    private lateinit var buttonMultiply: MaterialButton
    private lateinit var buttonDivide: MaterialButton
    private lateinit var buttonPercent: MaterialButton
    private lateinit var buttonClear: MaterialButton
    private lateinit var buttonOFF: MaterialButton
    private lateinit var buttonEqual: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        decimalFormat = DecimalFormat("#.##########")

        inputDisplay = findViewById(R.id.input)
        outputDisplay = findViewById(R.id.output)

        button0 = findViewById(R.id.btn0)
        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)
        button5 = findViewById(R.id.btn5)
        button6 = findViewById(R.id.btn6)
        button7 = findViewById(R.id.btn7)
        button8 = findViewById(R.id.btn8)
        button9 = findViewById(R.id.btn9)
        buttonDot = findViewById(R.id.btnPoint)
        buttonAdd = findViewById(R.id.add)
        buttonSub = findViewById(R.id.subtract)
        buttonMultiply = findViewById(R.id.multiply)
        buttonDivide = findViewById(R.id.division)
        buttonClear = findViewById(R.id.clear)
        buttonOFF = findViewById(R.id.off)
        buttonEqual = findViewById(R.id.equal)
        buttonPercent = findViewById(R.id.percent)

        val numberClickListener = { number: String ->
            inputDisplay.text = inputDisplay.text.toString() + number
        }

        button0.setOnClickListener { numberClickListener("0") }
        button1.setOnClickListener { numberClickListener("1") }
        button2.setOnClickListener { numberClickListener("2") }
        button3.setOnClickListener { numberClickListener("3") }
        button4.setOnClickListener { numberClickListener("4") }
        button5.setOnClickListener { numberClickListener("5") }
        button6.setOnClickListener { numberClickListener("6") }
        button7.setOnClickListener { numberClickListener("7") }
        button8.setOnClickListener { numberClickListener("8") }
        button9.setOnClickListener { numberClickListener("9") }

        val operatorClickListener = { operator: Char ->
            allCalculations()
            currentSymbol = operator
            outputDisplay.text = "${decimalFormat.format(firstValue)} $operator"
            inputDisplay.text = ""
        }

        buttonAdd.setOnClickListener { operatorClickListener(ADDITION) }
        buttonSub.setOnClickListener { operatorClickListener(SUBTRACTION) }
        buttonMultiply.setOnClickListener { operatorClickListener(MULTIPLICATION) }
        buttonDivide.setOnClickListener { operatorClickListener(DIVISION) }
        buttonPercent.setOnClickListener { operatorClickListener(PERCENT) }

        buttonDot.setOnClickListener {
            if (!inputDisplay.text.contains(".")) {
                inputDisplay.text = inputDisplay.text.toString() + "."
            }
        }

        buttonClear.setOnClickListener {
            if (inputDisplay.text.isNotEmpty()) {
                inputDisplay.text = inputDisplay.text.subSequence(0, inputDisplay.text.length - 1)
            } else {
                firstValue = Double.NaN
                secondValue = Double.NaN
                currentSymbol = '0'
                inputDisplay.text = ""
                outputDisplay.text = ""
            }
        }

        buttonOFF.setOnClickListener { finish() }

        buttonEqual.setOnClickListener {
            allCalculations()
            outputDisplay.text = decimalFormat.format(firstValue)
            firstValue = Double.NaN
            currentSymbol = '0'
        }
    }

    private fun allCalculations() {
        if (!java.lang.Double.isNaN(firstValue)) {
            if (inputDisplay.text.isNotEmpty()) {
                secondValue = inputDisplay.text.toString().toDouble()
                inputDisplay.text = ""
                firstValue = when (currentSymbol) {
                    ADDITION -> firstValue + secondValue
                    SUBTRACTION -> firstValue - secondValue
                    MULTIPLICATION -> firstValue * secondValue
                    DIVISION -> if (secondValue != 0.0) firstValue / secondValue else Double.NaN
                    PERCENT -> firstValue * (secondValue / 100)
                    else -> firstValue
                }
            }
        } else {
            try {
                firstValue = inputDisplay.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                firstValue = Double.NaN
            }
        }
    }

}
