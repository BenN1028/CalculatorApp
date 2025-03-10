package vcmsa.projects.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput = ""
    private var lastOperator = ""
    private var firstOperand = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Set up number buttons
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val button0: Button = findViewById(R.id.button0)

        // Set up operator buttons
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)

        // Set up clear and equals buttons
        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonEqual: Button = findViewById(R.id.buttonEqual)

        val buttonList = listOf(
            button1, button2, button3, button4, button5, button6, button7, button8, button9, button0
        )

        // Set up number buttons
        buttonList.forEach { button ->
            button.setOnClickListener {
                currentInput += button.text.toString()
                resultTextView.text = currentInput
            }
        }

        // Set up operator buttons
        buttonAdd.setOnClickListener { onOperatorClicked("+") }
        buttonSubtract.setOnClickListener { onOperatorClicked("-") }
        buttonMultiply.setOnClickListener { onOperatorClicked("*") }
        buttonDivide.setOnClickListener { onOperatorClicked("/") }

        // Clear button
        buttonClear.setOnClickListener {
            currentInput = ""
            firstOperand = ""
            lastOperator = ""
            resultTextView.text = "0"
        }

        // Equal button
        buttonEqual.setOnClickListener {
            if (firstOperand.isNotEmpty() && lastOperator.isNotEmpty()) {
                val result = performCalculation(firstOperand, currentInput, lastOperator)
                resultTextView.text = result.toString()
                currentInput = result.toString()
                firstOperand = ""
                lastOperator = ""
            }
        }
    }

    private fun onOperatorClicked(operator: String) {
        if (currentInput.isNotEmpty()) {
            if (firstOperand.isEmpty()) {
                firstOperand = currentInput
            } else {
                firstOperand = performCalculation(firstOperand, currentInput, lastOperator).toString()
            }
            lastOperator = operator
            currentInput = ""
        }
    }

    private fun performCalculation(firstOperand: String, secondOperand: String, operator: String): Double {
        val first = firstOperand.toDouble()
        val second = secondOperand.toDouble()
        return when (operator) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> first / second
            else -> 0.0
        }
    }
}