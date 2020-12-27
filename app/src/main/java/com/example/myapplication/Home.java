package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private Button finish;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        finish = findViewById(R.id.finish_btn);
        mAuth = FirebaseAuth.getInstance();
        finish.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(Home.this,MainActivity.class));
            finish();



        });
    }
}