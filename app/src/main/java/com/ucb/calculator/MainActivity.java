package com.ucb.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //declaring variables
    private EditText firstNumber;
    private EditText secondNumber;
    private TextView resultText;

    private DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Initialize views
        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        resultText = findViewById(R.id.resultText);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubstract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);

        //set click listeners for buttons
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        buttonSubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
    }
        private void calculate (char operator) {
            String num1 = firstNumber.getText().toString();
            String num2 = secondNumber.getText().toString();

            //verify if inputs are  valid
            if (!num1.isEmpty() && !num2.isEmpty()) {
                double firstNumber = Double.parseDouble(num1);
                double secondNumber = Double.parseDouble(num2);
                double result = 0;

                //perform calculation
                if (operator == '+') {
                    result = firstNumber + secondNumber;
                } else if (operator == '-'){
                    result = firstNumber - secondNumber;
                } else if (operator == '*'){
                    result = firstNumber * secondNumber;
                } else if (operator == '/'){
                    if (secondNumber != 0  ) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultText.setText(R.string.error_division_by_zero);
                        return;
                    }
                }

            //display and formatted in to 2 decimal
                resultText.setText(df.format(result));
            } else {
                resultText.setText(R.string.error_empty_input);
            }
        }
    }
