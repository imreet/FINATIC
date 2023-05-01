package com.example.take3;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class FD extends AppCompatActivity {

    EditText editTextInvestment, editTextInterest, editTextTenure;
    Button buttonCalculateFD,buttonReset,buttonDetails;
    TextView textViewFDResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd);

        editTextInvestment = findViewById(R.id.editTextInvestment);
        editTextInterest = findViewById(R.id.editTextInterest);
        editTextTenure = findViewById(R.id.editTextTenure);
        buttonCalculateFD = findViewById(R.id.buttonCalculateFD);
        buttonReset = findViewById(R.id.buttonReset);
        buttonDetails = findViewById(R.id.buttonDetails);
        textViewFDResult = findViewById(R.id.textViewFDResult);


        buttonCalculateFD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double principal = Double.parseDouble(editTextInvestment.getText().toString());
                double interestRate = Double.parseDouble(editTextInterest.getText().toString());
                int tenure = Integer.parseInt(editTextTenure.getText().toString());

                double maturityAmount = calculateFD(principal, interestRate, tenure);

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String result = "Total Amount: Rs. " + decimalFormat.format(maturityAmount);
                textViewFDResult.setText(result);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInvestment.setText("");
                editTextInterest.setText("");
                editTextTenure.setText("");
                textViewFDResult.setText("");
            }
        });

        buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FD.this);
                builder.setTitle("Investment Details");
                builder.setMessage("This app calculates the maturity amount for Fixed Deposit (FD). " +
                        "FD involves investing a lump sum amount for a specified number of years. " +
                        "Enter the investment amount, interest rate, and tenure in years to calculate the maturity amount for each investment type.");
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public double calculateFD(double principal, double interestRate, int tenure) {
        double ratePerMonth = interestRate / (12 * 100);
        int totalMonths = tenure * 12;
        double maturityAmount = principal * Math.pow(1 + ratePerMonth, totalMonths);
        return maturityAmount;
    }

    public void openTools(View view) {
        startActivity(new Intent(this, Tools.class));
    }
}