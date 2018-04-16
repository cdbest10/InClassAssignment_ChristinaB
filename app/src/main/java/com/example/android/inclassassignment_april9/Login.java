package com.example.android.inclassassignment_april9;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private String username;
    private String password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = (EditText) findViewById(R.id.email_login);
        passwordInput = (EditText) findViewById(R.id.password);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view){
        username = usernameInput.getText().toString();
        password = passwordInput.getText().toString();

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, task.getResult().getUser().getEmail() + " logged in successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void signUp(View view){
        username = usernameInput.getText().toString();
        password = passwordInput.getText().toString();

        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login.this, task.getResult().getUser().getEmail() + "signed up successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
}}
