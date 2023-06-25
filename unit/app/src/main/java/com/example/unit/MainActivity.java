package com.example.unit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PublicKey;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    EditText e1;
    TextView t1;
    Float f1;
    double f2;
    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 =findViewById(R.id.bcf);
        b2 =findViewById(R.id.bfc);
        b3 =findViewById(R.id.bck);
        b4 =findViewById(R.id.bkc);
        b5 =findViewById(R.id.bfk);
        b6 =findViewById(R.id.bkf);
        e1=findViewById(R.id.ip);
        t1= findViewById(R.id.op);
        df = new DecimalFormat("0.000000");


    }

    @SuppressLint("SetTextI18n")
    public void f2c(View view) {
        f1 = Float.parseFloat(e1.getText().toString());
        f2= ((f1-32)*5)/9;
        t1.setText("Result: "+(df.format(f2))+" C");
    }

    public void c2f(View view) {
        f1 = Float.parseFloat(e1.getText().toString());
        f2= ((f1*9)/5)+32;
        t1.setText("Result: "+(df.format(f2))+" F");
    }

    public void c2k(View view) {
        f1 = Float.parseFloat(e1.getText().toString());
        f2= f1+273.15;
        t1.setText("Result: "+(df.format(f2))+" K");
    }

    public void k2c(View view) {
        f1 = Float.parseFloat(e1.getText().toString());
        f2= f1-273.15;
        t1.setText("Result: "+(df.format(f2))+" C");
    }

    public void f2k(View view) {
        f1 = Float.parseFloat(e1.getText().toString());
        f2= (((f1-32)*5)/9)+273.15;
        t1.setText("Result: "+(df.format(f2))+" K");
    }

    public void k2f(View view) {
        f1 = Float.parseFloat(e1.getText().toString());
        f2= (((f1-273.15)*9)/5)+32;
        t1.setText("Result: "+(df.format(f2))+" F");
    }
}