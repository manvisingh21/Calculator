package com.nitttr.a_calculator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView displayText;
    private StringBuilder currentInput;
    private double result;
    private String currentOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayText = findViewById(R.id.display_text);
        currentInput = new StringBuilder();
        result = 0;
        currentOperator = "";

        // Set OnClickListener for all buttons

        findViewById(R.id.btn_divide).setOnClickListener(onClickListener);
        findViewById(R.id.btn_multiply).setOnClickListener(onClickListener);
        findViewById(R.id.btn_delete).setOnClickListener(onClickListener);
        findViewById(R.id.btn_7).setOnClickListener(onClickListener);
        findViewById(R.id.btn_8).setOnClickListener(onClickListener);
        findViewById(R.id.btn_9).setOnClickListener(onClickListener);
        findViewById(R.id.btn_subtract).setOnClickListener(onClickListener);
        findViewById(R.id.btn_4).setOnClickListener(onClickListener);
        findViewById(R.id.btn_5).setOnClickListener(onClickListener);
        findViewById(R.id.btn_6).setOnClickListener(onClickListener);
        findViewById(R.id.btn_add).setOnClickListener(onClickListener);
        findViewById(R.id.btn_1).setOnClickListener(onClickListener);
        findViewById(R.id.btn_2).setOnClickListener(onClickListener);
        findViewById(R.id.btn_3).setOnClickListener(onClickListener);
        findViewById(R.id.btn_equals).setOnClickListener(onClickListener);
        findViewById(R.id.btn_decimal).setOnClickListener(onClickListener);
        findViewById(R.id.btn_0).setOnClickListener(onClickListener);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            switch (buttonText) {
                case "C":
                    clearDisplay();
                    break;
                case "⌫":
                    deleteLastCharacter();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    handleOperator(buttonText);
                    break;
                case "=":
                    calculateResult();
                    break;
                default:
                    appendToInput(buttonText);
                    break;
            }
        }
    };


    private void appendToInput(String value) {
        currentInput.append(value);
        displayText.setText(currentInput.toString());
    }

    private void clearDisplay() {
        currentInput.setLength(0);
        result = 0;
        currentOperator = "";
        displayText.setText("");
    }

    private void deleteLastCharacter() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            displayText.setText(currentInput.toString());
        }
    }

    private void handleOperator(String operator) {
        if (currentInput.length() > 0) {
            result = Double.parseDouble(currentInput.toString());
            currentInput.setLength(0);
            currentOperator = operator;
        }
    }

    private void calculateResult() {
        if (currentInput.length() > 0 && !currentOperator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput.toString());
            switch (currentOperator) {
                case "+":
                    result += secondOperand;
                    break;
                case "-":
                    result -= secondOperand;
                    break;
                case "*":
                    result *= secondOperand;
                    break;

                case "=":
                    calculateResult();
                    break;

                case "/":
                    if (secondOperand != 0) {
                        result /= secondOperand;
                    } else {
                        displayText.setText("Error");
                        return;
                    }
                    break;
            }
            currentInput.setLength(0);
            currentInput.append(result);
            displayText.setText(currentInput.toString());
        }
    }
}
