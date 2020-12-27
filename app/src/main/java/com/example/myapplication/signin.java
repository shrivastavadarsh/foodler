package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {
    private EditText mEmail,mPass;
    private TextView mTextView;
    private Button signIpBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mEmail = findViewById(R.id.email_login);
        mPass = findViewById(R.id.pass_login);
        mTextView = findViewById(R.id.loginTosignup);
        signIpBtn = findViewById(R.id.signin_btn);

        mAuth = FirebaseAuth.getInstance();

//simply setting things for returning back if registration not completed from login page
        mTextView.setOnClickListener(v -> startActivity(new Intent(signin.this,MainActivity.class)));
//        setting onclick function on login Button;

        signIpBtn.setOnClickListener(v -> logInUser());


    }

    private void logInUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(signin.this,"Login Successful !!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signin.this,Home.class));
                                finish();

                            }
                        }).addOnFailureListener(e -> {
                            Toast.makeText(signin.this,"Error Occured !!",Toast.LENGTH_SHORT).show();

                        });
            }
            else{
                mPass.setError("Empty fields not allowed");
            }
        }
        else if (email.isEmpty()){
            mEmail.setError("Email cannot be Empty");
        }
        else{
            mEmail.setError("please enter correct Email");
        }


    }


}