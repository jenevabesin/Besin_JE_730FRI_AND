package com.ucb.bottomnavigation.ui.calculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ucb.bottomnavigation.R;

import java.text.DecimalFormat;

public class CalculatorFragment extends Fragment {

    // Declaring variables
    private EditText firstNumber;
    private EditText secondNumber;
    private TextView resultText;
    private DecimalFormat df = new DecimalFormat("#.00");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        // Initialize views
        firstNumber = view.findViewById(R.id.firstNumber);
        secondNumber = view.findViewById(R.id.secondNumber);
        resultText = view.findViewById(R.id.resultText);

        Button buttonAdd = view.findViewById(R.id.buttonAdd);
        Button buttonSubtract = view.findViewById(R.id.buttonSubtract);
        Button buttonMultiply = view.findViewById(R.id.buttonMultiply);
        Button buttonDivide = view.findViewById(R.id.buttonDivide);
        Button buttonClear = view.findViewById(R.id.buttonClear); // Initialize Clear Button

        // Set click listeners for buttons
        buttonAdd.setOnClickListener(v -> calculate('+'));
        buttonSubtract.setOnClickListener(v -> calculate('-'));
        buttonMultiply.setOnClickListener(v -> calculate('*'));
        buttonDivide.setOnClickListener(v -> calculate('/'));
        buttonClear.setOnClickListener(v -> clearFields());

        return view;
    }

    private void calculate(char operator) {
        String num1 = firstNumber.getText().toString();
        String num2 = secondNumber.getText().toString();

        // Verify if inputs are valid
        if (!num1.isEmpty() && !num2.isEmpty()) {
            double firstNum = Double.parseDouble(num1);
            double secondNum = Double.parseDouble(num2);
            double result = 0;

            // Perform calculation
            if (operator == '+') {
                result = firstNum + secondNum;
            } else if (operator == '-') {
                result = firstNum - secondNum;
            } else if (operator == '*') {
                result = firstNum * secondNum;
            } else if (operator == '/') {
                if (secondNum != 0) {
                    result = firstNum / secondNum;
                } else {
                    resultText.setText(R.string.error_division_by_zero);
                    return;
                }
            }

            // Display result formatted to 2 decimal places
            resultText.setText(df.format(result));
        } else {
            resultText.setText(R.string.error_empty_input);
        }
    }

    private void clearFields() {
        // Clear the input fields and result
        firstNumber.setText("");
        secondNumber.setText("");
        resultText.setText(R.string.result_text); // Reset the result text to its initial state
    }
}