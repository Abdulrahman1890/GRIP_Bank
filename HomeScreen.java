package com.example.bank_thespark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);

        getSupportActionBar().hide();



    }

    public void viewCustomers(View view) {
        startActivity(new Intent(HomeScreen.this,ViewAllCustomers.class));
        finish();
    }
}