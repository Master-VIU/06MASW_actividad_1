package com.example.mycalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log.d
import android.widget.Button
import android.widget.TextView
import com.github.ajalt.timberkt.d

class MainActivity : AppCompatActivity() {

    var result: Float? = null
    var storedNumber: Float? = null
    var currentNumber: String = ""
    var operation: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.expand).setOnClickListener {
            d { "Expand button clicked" }
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(MainActivity2.RESULT_CAL, currentNumber)
            startActivity(intent)
        }

        findViewById<Button>(R.id.one).setOnClickListener {
            currentNumber = currentNumber.plus("1")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.two).setOnClickListener {
            currentNumber = currentNumber.plus("2")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.three).setOnClickListener {
            currentNumber = currentNumber.plus("3")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.four).setOnClickListener {
            currentNumber = currentNumber.plus("4")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.five).setOnClickListener {
            currentNumber = currentNumber.plus("5")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.six).setOnClickListener {
            currentNumber = currentNumber.plus("6")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.seven).setOnClickListener {
            currentNumber = currentNumber.plus("7")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.eight).setOnClickListener {
            currentNumber = currentNumber.plus("8")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.nine).setOnClickListener {
            currentNumber = currentNumber.plus("9")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.zero).setOnClickListener {
            currentNumber = currentNumber.plus("0")
            findViewById<TextView>(R.id.result).text = currentNumber
        }

        findViewById<Button>(R.id.spot).setOnClickListener {
            if (!currentNumber.contains(".")) {
                currentNumber = currentNumber.plus(".")
                findViewById<TextView>(R.id.result).text = currentNumber
            }
        }

        findViewById<Button>(R.id.subtract).setOnClickListener {
            if (currentNumber != "") {
                if (storedNumber == null) {
                    operation = "-"
                    storedNumber = currentNumber.toFloat()
                    currentNumber = ""
                } else {
                    result = performOperation(storedNumber, currentNumber, operation)
                    storedNumber = result
                    findViewById<TextView>(R.id.result).text = storedNumber.toString()
                    currentNumber = ""
                    operation = "-"
                }
            } else {
                operation = "-"
            }
        }

        findViewById<Button>(R.id.add).setOnClickListener {
            if (currentNumber != "") {
                if (storedNumber == null) {
                    operation = "+"
                    storedNumber = currentNumber.toFloat()
                    currentNumber = ""
                } else {
                    result = performOperation(storedNumber, currentNumber, operation)
                    storedNumber = result
                    findViewById<TextView>(R.id.result).text = storedNumber.toString()
                    currentNumber = ""
                    operation = "+"
                }
            } else {
                operation = "+"
            }
        }

        findViewById<Button>(R.id.divide).setOnClickListener {
            if (currentNumber != "") {
                if (storedNumber == null) {
                    operation = "/"
                    storedNumber = currentNumber.toFloat()
                    currentNumber = ""
                } else {
                    result = performOperation(storedNumber, currentNumber, operation)
                    storedNumber = result
                    findViewById<TextView>(R.id.result).text = storedNumber.toString()
                    currentNumber = ""
                    operation = "/"
                }
            } else {
                operation = "/"
            }
        }

        findViewById<Button>(R.id.multiply).setOnClickListener {
            if (currentNumber != "") {
                if (storedNumber == null) {
                    operation = "x"
                    storedNumber = currentNumber.toFloat()
                    currentNumber = ""
                } else {
                    result = performOperation(storedNumber, currentNumber, operation)
                    storedNumber = result
                    findViewById<TextView>(R.id.result).text = storedNumber.toString()
                    currentNumber = ""
                    operation = "x"
                }
            } else {
                operation = "x"
            }
        }

        findViewById<Button>(R.id.erase).setOnClickListener {
            if (currentNumber.isNotEmpty()) {
               currentNumber = currentNumber.substring(0, currentNumber.length - 1)
               findViewById<TextView>(R.id.result).text = currentNumber
            }
        }

        findViewById<Button>(R.id.ac).setOnClickListener {
            currentNumber = ""
            operation = null
            result = null
            storedNumber = null
            findViewById<TextView>(R.id.result).text = ""
        }

        findViewById<Button>(R.id.equals).setOnClickListener {
            result = performOperation(storedNumber, currentNumber, operation)
            storedNumber = null
            currentNumber = result.toString()
            operation = null
            if (result != null) {
                findViewById<TextView>(R.id.result).text = trimTrailingZero(currentNumber)
            } else {
                findViewById<TextView>(R.id.result).text = R.string.invalid_operation.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    private fun performOperation(storedNumber: Float?, currentNumber: String, operation: String?): Float? {

        if (operation == null || operation.isEmpty()) {
            d { "Error: no se ha definido ninguna operacion"}
            return if (currentNumber.isNotEmpty()) {
                currentNumber.toFloat()
            } else {
                0.toFloat()
            }
        }

        if (storedNumber != null) {
            return when (operation) {
                "+" -> (storedNumber + currentNumber.toFloat())
                "-" -> (storedNumber - currentNumber.toFloat())
                "x" -> (storedNumber * currentNumber.toFloat())
                "/" -> (storedNumber / currentNumber.toFloat())
                else -> {
                    d { "Error: Operacion no permitida" }
                    null
                }
            }
        }
        d { "Error: No hay numero almacenado" }
        return null
    }

    private fun trimTrailingZero(value: String?): String? {
        return if (!value.isNullOrEmpty()) {
            if (value.indexOf(".") < 0) {
                value
            } else {
                value.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
            }
        } else {
            value
        }
    }

}