package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.apache.commons.math3.util.Precision;

public class MainActivity extends AppCompatActivity {

    TextInputEditText num1Inp, num2Inp;
    TextView resultView, operatorSelected;
    Button addButton, subButton, mulButton, divButton, expButton, factButton, modButton, clearButton;
    double num1, num2;
    final int precision = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        num1Inp = findViewById(R.id.num1Input);
        num2Inp = findViewById(R.id.num2Input);
        resultView = findViewById(R.id.resultView);
        operatorSelected = findViewById(R.id.operatorSelected);
        addButton = findViewById(R.id.addButton);
        subButton = findViewById(R.id.subButton);
        divButton = findViewById(R.id.divButton);
        mulButton = findViewById(R.id.mulButton);
        expButton = findViewById(R.id.expButton);
        factButton = findViewById(R.id.factButton);
        modButton = findViewById(R.id.modButton);
        clearButton = findViewById(R.id.clearButton);
        setListeners();
    }

    private void setListeners() {
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operatorSelected.setText("+");
                if(updateInputs()){
                    String res = "= " + String.valueOf(Precision.round(num1 + num2, precision));
                    resultView.setText(res);
                }
            }
        });

        subButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operatorSelected.setText("-");
                if(updateInputs()){
                    String res = "= " + String.valueOf(Precision.round(num1 - num2, precision));
                    resultView.setText(res);
                }
            }
        });

        mulButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operatorSelected.setText("x");
                if(updateInputs()){
                    String res = "= " + String.valueOf(Precision.round(num1 * num2, precision));
                    resultView.setText(res);
                }
            }
        });

        divButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operatorSelected.setText("÷");
                if(updateInputs()){
                    String res = "= " + String.valueOf(Precision.round(num1 / num2, precision));
                    resultView.setText(res);
                }
            }
        });

        expButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operatorSelected.setText("^");
                if(updateInputs()){
                    String res = "= " + String.valueOf(Precision.round(Math.pow(num1, num2), precision));
                    resultView.setText(res);
                }
            }
        });

        factButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operatorSelected.setText("");
                String num1_S = num1Inp.getText().toString();
                String num2_S = num2Inp.getText().toString();
                if(!num1_S.isEmpty()){
                    num2Inp.setText("");
                    int num = Integer.parseInt(num1_S);
                    if (num < 0) {
                        Toast.makeText(MainActivity.this, "Factorial of -ve numbers does not exist!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String res = num + "! = " + fact(num);
                    resultView.setText(res);
                } else if(!num2_S.isEmpty()){
                    num1Inp.setText("");
                    int num = Integer.parseInt(num2_S);
                    if (num < 0) {
                        Toast.makeText(MainActivity.this, "Factorial of -ve numbers does not exist!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String res = num + "! = " + fact(num);
                    resultView.setText(res);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a number!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        modButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operatorSelected.setText("%");
                if(updateInputs()){
                    String res = "= " + (num1 % num2);
                    resultView.setText(res);
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resultView.setText("");
                num1Inp.setText("");
                num2Inp.setText("");
                operatorSelected.setText("");
            }
        });
    }

    private boolean updateInputs() {
        String num1_S = num1Inp.getText().toString();
        String num2_S = num2Inp.getText().toString();
        if (!num1_S.isEmpty() && !num2_S.isEmpty()) {
            num1 = Double.parseDouble(num1_S);
            num2 = Double.parseDouble(num2_S);
            return true;
        }
        Toast.makeText(this, "Please enter both numbers!", Toast.LENGTH_SHORT).show();
        return false;
    }

    private int fact(int n) {
        if (n <= 1)
            return 1;
        return n * fact(n-1);
    }
}