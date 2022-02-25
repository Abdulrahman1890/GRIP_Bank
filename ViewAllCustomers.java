package com.example.bank_thespark;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewAllCustomers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arr;
    private ViewCustomersAdapter viewCustomersAdapter;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_customers);

        getSupportActionBar().hide();


        recyclerView = findViewById(R.id.customer_recycle_view);
        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        arr = MyDatabase.getInstance(getApplicationContext()).getAllUser();
        viewCustomersAdapter = new ViewCustomersAdapter(arr);
        recyclerView.setAdapter(viewCustomersAdapter);
        viewCustomersAdapter.notifyDataSetChanged();



    }
}