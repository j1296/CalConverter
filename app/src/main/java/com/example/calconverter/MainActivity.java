// ==========================================
//  Title:  MainActivity
//  Author: James Kelsey
//  Date:   03/05/2020
// ==========================================
package com.example.calconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Constructs activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On click for calculator button
        Button calculatorButton = findViewById(R.id.calculatorButton);
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCalculatorActivity();
            }
        });

        // On click for converter button
        Button converterButton = findViewById(R.id.converterButton);
        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startConverterActivity();
            }
        });

        // On click for about button
        TextView aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAboutActivity();
            }
        });

        // On click for rates button
        Button ratesButton = findViewById(R.id.ratesButton);
        ratesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRatesActivity();
            }
        });
    }

    // Starts CalculatorActivity
    public void startCalculatorActivity() {
        Intent calculatorIntent = new Intent(MainActivity.this, CalculatorActivity.class);
        startActivity(calculatorIntent);
    }

    // Starts ConverterActivity
    public void startConverterActivity() {
        Intent converterIntent = new Intent(MainActivity.this, ConverterActivity.class);
        startActivity(converterIntent);
    }

    // Starts AboutActivity
    public void startAboutActivity() {
        Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(aboutIntent);
    }

    // Starts RatesActivity
    public void startRatesActivity() {
        Intent ratesIntent = new Intent(MainActivity.this, RatesActivity.class);
        startActivity(ratesIntent);
    }
}
