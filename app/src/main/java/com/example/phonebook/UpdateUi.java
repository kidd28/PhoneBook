package com.example.phonebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateUi extends AppCompatActivity {

    EditText name, address, phone;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ui);

        name = findViewById(R.id.Name);
        address = findViewById(R.id.Address);
        phone = findViewById(R.id.Phone);
        save = findViewById(R.id.Save);

        String na = getIntent().getStringExtra("Name");
        String ad = getIntent().getStringExtra("Address");
        String ph = getIntent().getStringExtra("Phone");
        String id = getIntent().getStringExtra("Id");

        name.setText(na);
        address.setText(ad);
        phone.setText(ph);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name, Adress, Phone;
                Name = name.getText().toString();
                Adress = address.getText().toString();
                Phone = phone.getText().toString();

                DatabaseReference database = FirebaseDatabase.getInstance().getReference("Contacts");
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("Name", Name);
                        hashMap.put("Address", Adress);
                        hashMap.put("Phone", Phone);
                        hashMap.put("Id", id);
                        database.child(id).setValue(hashMap);
                        Toast.makeText(UpdateUi.this, "Saved", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(UpdateUi.this, Dashboard.class);
                        startActivity(intent);
                        UpdateUi.this.finish();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UpdateUi.this, Update.class));
        UpdateUi.this.finish();
    }
}