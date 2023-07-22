package com.example.phonebook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeleteContactAdapter extends RecyclerView.Adapter<DeleteContactAdapter.HolderAdapter> {
    Context context;
    ArrayList<ContactModel> contactModels;

    public DeleteContactAdapter(Context context , ArrayList<ContactModel> contactModels){
        this.context= context;
        this.contactModels=contactModels;
    }
    @NonNull
    @Override
    public DeleteContactAdapter.HolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);


        return new DeleteContactAdapter.HolderAdapter(v);    }

    @Override
    public void onBindViewHolder(@NonNull DeleteContactAdapter.HolderAdapter holder, int position) {
        ContactModel contactModel = contactModels.get(position);
        String name = contactModel.getName();
        String address = contactModel.getAddress();
        String phone = contactModel.getPhone();

        holder.name.setText(name);
        holder.address.setText("Address: "+address);
        holder.phone.setText("Phone: "+phone);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose Action");
                builder.setMessage("Are you sure you want to delete this book?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Contacts").child(contactModel.getId());
                        databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                                context.startActivity(new Intent(context, Dashboard.class));
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }

    public class HolderAdapter extends RecyclerView.ViewHolder {

        TextView name,address,phone;
        CardView layout;
        public HolderAdapter(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            address= itemView.findViewById(R.id.address);
            phone= itemView.findViewById(R.id.phone);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
