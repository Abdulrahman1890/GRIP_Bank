package com.example.bank_thespark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;

public class TransactionSend extends AppCompatActivity {

    int id;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Transfer> transfers;
    private SendAdapter sendAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_send);

        getSupportActionBar().hide();


        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
        transfers = MyDatabase.getInstance(getApplicationContext()).getTransferAsSender(id);
        recyclerView = findViewById(R.id.sends_recycle_view);
        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        sendAdapter = new SendAdapter(transfers, "sender", getApplicationContext());
        recyclerView.setAdapter(sendAdapter);
        sendAdapter.notifyDataSetChanged();
    }
}