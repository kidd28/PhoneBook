package com.example.phonebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Update extends AppCompatActivity {
    ArrayList<ContactModel> contactModels;
    UpdateContactAdapter updateContactAdapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        rv = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Update.this);
        rv.setLayoutManager(layoutManager);
        contactModels = new ArrayList<>();

        loadContacts();
    }
    private void loadContacts() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Contacts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactModels.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ContactModel model = ds.getValue(ContactModel.class);
                    contactModels.add(model);
                }
                updateContactAdapter = new UpdateContactAdapter(Update.this, contactModels);
                rv.setAdapter(updateContactAdapter);
                updateContactAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Update.this, Dashboard.class));
        Update.this.finish();
    }

}