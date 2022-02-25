package com.example.bank_thespark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewCustomersAdapter extends RecyclerView.Adapter<ViewCustomersAdapter.ViewHolder> {

    ArrayList<User> users;
    public ViewCustomersAdapter(ArrayList<User> users) {
        this.users=users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(vv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.getUsername());
        holder.amount.setText(String.valueOf(user.getCurrentBalance())+"$");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),CustomerAccount.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",user.getId());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,amount;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            name = itemView.findViewById(R.id.name_customer);
            amount = itemView.findViewById(R.id.amount_customer);

        }
    }
}
