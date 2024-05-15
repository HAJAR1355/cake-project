package com.example.cake;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class orderpage extends AppCompatActivity {
    DBHelper mydb;
    private EditText NameEditText, cakeNameEditText, AddressEditText, AnyDetailsEditText, deliveryDateEditText;
    private Button createButton, readButton, deleteButton, confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage);
        mydb = new DBHelper(this);

        // Initialize all variables.
        NameEditText = findViewById(R.id.nametext);
        AddressEditText = findViewById(R.id.addresstext);
        cakeNameEditText = findViewById(R.id.Caketext);
        deliveryDateEditText = findViewById(R.id.date);
        AnyDetailsEditText = findViewById(R.id.option);
        createButton = findViewById(R.id.CreateButton);
        readButton = findViewById(R.id.ReadButton);
        deleteButton = findViewById(R.id.deleteButton);
        confirmButton = findViewById(R.id.confirmButton);

        // Set listeners for buttons
        insertData();
        showData();
        deleteData();
        confirmOrder(); // Call the method to set up click listener for confirm button
    }

    public void insertData() {
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = mydb.insertData(
                        NameEditText.getText().toString(),
                        AddressEditText.getText().toString(),
                        cakeNameEditText.getText().toString(),
                        AnyDetailsEditText.getText().toString(),
                        deliveryDateEditText.getText().toString());
                if (inserted) {
                    Toast.makeText(orderpage.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(orderpage.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showData() {
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mydb.getData();
                if (cursor == null || cursor.getCount() == 0) {
                    showMessage("Error", "No data found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Name: ").append(cursor.getString(1)).append("\n");
                    buffer.append("ADDRESS: ").append(cursor.getString(2)).append("\n");
                    buffer.append("CAKE NAME: ").append(cursor.getString(3)).append("\n");
                    buffer.append("ANY DETAILS: ").append(cursor.getString(4)).append("\n");
                    buffer.append("DELIVERY DATE: ").append(cursor.getString(5)).append("\n\n");
                }
                showMessage("Data", buffer.toString());
                cursor.close(); // Close the cursor after use
            }
        });
    }

    public void deleteData() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = NameEditText.getText().toString();
                Integer deleted = mydb.deleteData(name);
                if (deleted > 0) {
                    Toast.makeText(orderpage.this, "Data with Name " + name + " deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(orderpage.this, "Failed to delete data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void confirmOrder() {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ThankActivity when the confirm button is clicked
                Intent intent = new Intent(orderpage.this, thank.class);
                startActivity(intent);
            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title)
                .setMessage(message)
                .show();
    }
}
