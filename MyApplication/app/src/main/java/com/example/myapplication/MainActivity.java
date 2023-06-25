package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView expressionTextView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionTextView = findViewById(R.id.exp);
        resultTextView = findViewById(R.id.result);

        setButtonClickListeners();
    }


    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "=":
                calculateExpression();
                break;
            case "AC":
                clearExpression();
                break;
            case "C":
                clearLastCharacter();
                break;
            default:
                appendToExpression(buttonText);
                break;
        }
    }

    private void setButtonClickListeners() {
        int[] buttonIds = {
                R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7,
                R.id.b8, R.id.b9, R.id.badd, R.id.bsub, R.id.bmull, R.id.bdiv,
                R.id.bop, R.id.bcp, R.id.bdec
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }

        Button equalsButton = findViewById(R.id.beq);
        equalsButton.setOnClickListener(this);

        Button clearButton = findViewById(R.id.bac);
        clearButton.setOnClickListener(this);

        Button deleteButton = findViewById(R.id.bc);
        deleteButton.setOnClickListener(this);
    }

    private void appendToExpression(String text) {
        String currentExpression = expressionTextView.getText().toString();
        expressionTextView.setText(currentExpression + text);
    }

    private void clearExpression() {
        expressionTextView.setText("");
        resultTextView.setText("0");
    }

    private void clearLastCharacter() {
        String currentExpression = expressionTextView.getText().toString();
        if (!currentExpression.isEmpty()) {
            String newExpression = currentExpression.substring(0, currentExpression.length() - 1);
            expressionTextView.setText(newExpression);
        }
    }

    private void calculateExpression() {
        String expression = expressionTextView.getText().toString();

        try {
            double result = evaluateExpression(expression);
            resultTextView.setText(String.valueOf(result));
        } catch (Exception e) {
            resultTextView.setText("Error");
        }
    }

    private double evaluateExpression(String expression) throws Exception {
        expression = expression.replaceAll("\\s+", "");

        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");
        }

        try {
            while (expression.contains("(")) {
                int openingIndex = expression.lastIndexOf('(');
                int closingIndex = expression.indexOf(')', openingIndex);

                if (closingIndex == -1) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }

                String subExpression = expression.substring(openingIndex + 1, closingIndex);
                double subResult = evaluateExpression(subExpression);

                expression = expression.substring(0, openingIndex) + subResult + expression.substring(closingIndex + 1);
            }

            Expression exp = new ExpressionBuilder(expression)
                    .variables("x") // Define a variable 'x' for handling negative numbers
                    .build()
                    .setVariable("x", 0);

            return exp.evaluate();
        } catch (Exception e) {
            throw new Exception("Error evaluating expression");
        }
    }




}
