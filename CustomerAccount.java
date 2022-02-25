package com.example.bank_thespark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerAccount extends AppCompatActivity {

    TextView name,email,balance,send,recieve;
    User user;
    MyDatabase db;
    private int id;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);

        getSupportActionBar().hide();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        balance = findViewById(R.id.balance);
        send = findViewById(R.id.send);
        recieve = findViewById(R.id.recieve);


        db = MyDatabase.getInstance(getApplicationContext());


        b = getIntent().getExtras();
        id = b.getInt("id");
        user = db.getUser(id);
        name.setText(user.getUsername());
        email.setText(user.getEmail());
        balance.setText(String.valueOf(user.getCurrentBalance())+"$");
        send.setText(String.valueOf(db.getNumOfSending(user)));
        recieve.setText(String.valueOf(db.getNumOfReceiving(user)));

    }

    public void sendTransactions(View view) {
        startActivity(new Intent(CustomerAccount.this,TransactionSend.class).putExtras(b));
    }

    public void receiveTransactions(View view) {
        startActivity(new Intent(CustomerAccount.this,TransactionReceive.class).putExtras(b));
    }




    public void enterAmount(View view) {
        AlertDialog.Builder d = new AlertDialog.Builder(CustomerAccount.this);
        View v = getLayoutInflater().inflate(R.layout.transfer_amount,null);
            d.setTitle("Enter Amount")
            .setView(v)
            .setPositiveButton("enter", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText editText = v.findViewById(R.id.enter_money);
                    float amount = Float.parseFloat(editText.getText().toString());
                    if(amount <= user.getCurrentBalance() && amount>0){
                    b.putFloat("amount",amount);
                    startActivity(new Intent(CustomerAccount.this,CustomerToTransfer.class).putExtras(b));
                    finish();
                    }
                    else{
                        Toast.makeText(CustomerAccount.this, "Invalid Amount", Toast.LENGTH_SHORT).show();
                    }
                }
            })
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
        d.show();
    }

}