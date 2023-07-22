package com.example.phonebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UpdateContactAdapter extends RecyclerView.Adapter<UpdateContactAdapter.HolderAdapter> {
    Context context;
    ArrayList<ContactModel> contactModels;

    public UpdateContactAdapter(Context context , ArrayList<ContactModel> contactModels){
        this.context= context;
        this.contactModels=contactModels;
    }

    @NonNull
    @Override
    public UpdateContactAdapter.HolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);


        return new UpdateContactAdapter.HolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateContactAdapter.HolderAdapter holder, int position) {
        ContactModel contactModel = contactModels.get(position);
        String name = contactModel.getName();
        String address = contactModel.getAddress();
        String phone = contactModel.getPhone();

        holder.name.setText(name);
        holder.address.setText("Address: "+address);
        holder.phone.setText("Phone: "+phone);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, UpdateUi.class);
                intent.putExtra("Name", name);
                intent.putExtra("Address", address);
                intent.putExtra("Phone", phone);
                intent.putExtra("Id", contactModel.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
       return contactModels.size();
    }
    public class HolderAdapter extends RecyclerView.ViewHolder {
        TextView name,address,phone;
        CardView cv;
        public HolderAdapter(@NonNull View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.name);
            address= itemView.findViewById(R.id.address);
            phone= itemView.findViewById(R.id.phone);
            cv= itemView.findViewById(R.id.layout);

        }
    }
}
