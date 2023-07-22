package com.example.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.HolderAdapter> {
    Context context;
    ArrayList<ContactModel> contactModels;

    public ContactListAdapter(Context context , ArrayList<ContactModel> bookModels){
        this.context= context;
        this.contactModels=bookModels;
    }
    @NonNull
    @Override
    public ContactListAdapter.HolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);
        return new ContactListAdapter.HolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.HolderAdapter holder, int position) {
        ContactModel contactModel = contactModels.get(position);
        String name = contactModel.getName();
        String address = contactModel.getAddress();
        String phone = contactModel.getPhone();

        holder.name.setText(name);
        holder.address.setText("Address: "+address);
        holder.phone.setText("Phone: "+phone);
    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }


    public class HolderAdapter extends RecyclerView.ViewHolder {
        TextView name,address,phone;
        public HolderAdapter(@NonNull View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.name);
            address= itemView.findViewById(R.id.address);
            phone= itemView.findViewById(R.id.phone);

        }
    }
}
