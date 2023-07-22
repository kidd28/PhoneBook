package com.example.phonebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Create extends AppCompatActivity {

    EditText name, address, phone;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        name = findViewById(R.id.Name);
        address = findViewById(R.id.Address);
        phone = findViewById(R.id.Phone);
        save = findViewById(R.id.Save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name, Adress, Phone;
                Name = name.getText().toString();
                Adress = address.getText().toString();
                Phone = phone.getText().toString();
                String id = String.valueOf(System.currentTimeMillis());

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
                            Toast.makeText(Create.this, "Added", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Create.this, Dashboard.class);
                            startActivity(intent);
                            Create.this.finish();

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
        startActivity(new Intent(Create.this, Dashboard.class));
        Create.this.finish();
    }
}