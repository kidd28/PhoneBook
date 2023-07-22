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

public class Read extends AppCompatActivity {
    ArrayList<ContactModel> contactModels;
    ContactListAdapter contactListAdapter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        rv = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Read.this);
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
                contactListAdapter = new ContactListAdapter(Read.this, contactModels);
                rv.setAdapter(contactListAdapter);
                contactListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Read.this, Dashboard.class));
        Read.this.finish();
    }
}