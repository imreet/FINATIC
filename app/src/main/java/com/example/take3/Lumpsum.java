package com.example.take3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Lumpsum extends AppCompatActivity {

    EditText principalInput;
    EditText rateInput;
    EditText timeInput;
    Button calculateButton, buttonReset;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lumpsum);

        principalInput = findViewById(R.id.principal_input);
        rateInput = findViewById(R.id.rate_input);
        timeInput = findViewById(R.id.time_input);
        calculateButton = findViewById(R.id.calculate_button);
        resultTextView = findViewById(R.id.result_text_view);
        buttonReset = findViewById(R.id.buttonReset);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLumpsum();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principalInput.setText("");
                rateInput.setText("");
                timeInput.setText("");
                resultTextView.setText("");
            }
        });
    }

    private void calculateLumpsum() {
        String principalString = principalInput.getText().toString();
        String rateString = rateInput.getText().toString();
        String timeString = timeInput.getText().toString();

        if (!TextUtils.isEmpty(principalString) && !TextUtils.isEmpty(rateString) && !TextUtils.isEmpty(timeString)) {
            float principal = Float.parseFloat(principalString);
            float rate = Float.parseFloat(rateString);
            float time = Float.parseFloat(timeString);

            float ratePerYear = rate / 100;
            float maturityAmount = principal * (1 + ratePerYear * time);

            resultTextView.setText(getString(R.string.result, String.valueOf(maturityAmount)));
        } else {
            Toast.makeText(this, "Please enter valid input values", Toast.LENGTH_SHORT).show();
        }
    }

    public void openTools(View view){startActivity(new Intent(this, Tools.class));}
}
