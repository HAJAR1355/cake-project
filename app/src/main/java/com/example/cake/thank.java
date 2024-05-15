package com.example.cake;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cake.R;

public class thank extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);

        // Initialize the TextView for the Instagram link
        TextView instagramLink = findViewById(R.id.instagramLink);
    }

    // Method to handle the click event of the Instagram link
    public void openInstagramProfile(View view) {
        // Instagram profile URL
        String instagramUrl = "https://www.instagram.com/cake_t_h_e_great?utm_source=qr&igsh=MTA0M3BwNzA3Mnh0NA==";

        // Create an Intent to open the Instagram profile
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
        // Set the package to Instagram
        intent.setPackage("com.instagram.android");

        // Try to open the Instagram app
        try {
            startActivity(intent);
        } catch (Exception e) {
            // If the Instagram app is not installed, open the Instagram website
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl)));
        }
    }
}
