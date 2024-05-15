package com.example.cake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Galary extends AppCompatActivity {
    Button orderNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galary);

        // Initialize the order now button
        orderNowButton = findViewById(R.id.ordernowButton);

        // Set click listener for the order now button
        orderNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the OrderActivity when the order now button is clicked
                Intent intent = new Intent(Galary.this, orderpage.class);
                startActivity(intent);
            }
        });
    }
}
