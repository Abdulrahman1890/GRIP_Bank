package com.example.bank_thespark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerToTransfer extends AppCompatActivity {

    BoundService boundService;
    User user = new User();
    private int id;
    float amount;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> usersarr;
    private CustomerToTransAdapter viewCustomersAdapter;
    String date = "";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_to_transfer);

        getSupportActionBar().hide();

        Intent intent = new Intent(getApplicationContext(),BoundService.class);
        //startService(intent);
        boolean flag = bindService(intent,connection, Context.BIND_AUTO_CREATE);

        recyclerView = findViewById(R.id.customertotrans_recycle_view);
        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);



        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
        amount = b.getFloat("amount");

    }
    private ServiceConnection connection = new ServiceConnection() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public synchronized void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
            boundService = myBinder.getService();
            date = boundService.getCurrentTime();
            usersarr = MyDatabase.getInstance(getApplicationContext()).getAllUser(id);
            viewCustomersAdapter = new CustomerToTransAdapter(usersarr , amount , id, date);
            recyclerView.setAdapter(viewCustomersAdapter);
            viewCustomersAdapter.notifyDataSetChanged();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}