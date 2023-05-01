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

public class SIP extends AppCompatActivity {

    EditText editTextInvestment, editTextInterest, editTextTenure;
    Button buttonCalculateSIP,buttonReset,buttonDetails;
    TextView textViewSIPResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sip);

        editTextInvestment = findViewById(R.id.editTextInvestment);
        editTextInterest = findViewById(R.id.editTextInterest);
        editTextTenure = findViewById(R.id.editTextTenure);
        buttonCalculateSIP = findViewById(R.id.buttonCalculateSIP);
        buttonReset = findViewById(R.id.buttonReset);
        buttonDetails = findViewById(R.id.buttonDetails);
        textViewSIPResult = findViewById(R.id.textViewSIPResult);

        buttonCalculateSIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double monthlyInvestment = Double.parseDouble(editTextInvestment.getText().toString());
                double interestRate = Double.parseDouble(editTextInterest.getText().toString());
                int tenure = Integer.parseInt(editTextTenure.getText().toString());

                double invested_amt = monthlyInvestment * tenure;
                double maturityAmount = calculateSIP(monthlyInvestment, interestRate, tenure);

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String result1 = "Maturity Amount: Rs. " + decimalFormat.format(maturityAmount);
                textViewSIPResult.setText(result1);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInvestment.setText("");
                editTextInterest.setText("");
                editTextTenure.setText("");
                textViewSIPResult.setText("");
            }
        });

        buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SIP.this);
                builder.setTitle("Investment Details");
                builder.setMessage("This app calculates the maturity amount for Systematic Investment Plan (SIP)" +
                        "SIP involves investing a fixed amount of money every month for a specified number of years. " +
                        "Enter the investment amount, interest rate, and tenure in years to calculate the maturity amount for each investment.");
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public double calculateSIP(double monthlyInvestment, double interestRate, int tenure) {
        double invested_amt;
        double ratePerMonth = interestRate / (12 * 100);
        int totalMonths = tenure * 12;
        double maturityAmount = 0;
        for(int i = 1; i <= totalMonths; i++) {
            maturityAmount += monthlyInvestment;
            double interest = maturityAmount * ratePerMonth;
            maturityAmount += interest;
        }

        return maturityAmount;
    }

    public void openTools(View view) {
        startActivity(new Intent(this, Tools.class));
    }

}
