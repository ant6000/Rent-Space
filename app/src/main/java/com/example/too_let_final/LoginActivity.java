package com.example.too_let_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button logintn;
    EditText editTextEmail,editTextPassword;
    String email, password;
    TextView RegisterText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // getSupportActionBar().setTitle("Find your Home");
        getSupportActionBar().hide();

        editTextEmail = findViewById(R.id.login_email);
        editTextPassword = findViewById(R.id.login_pass);
        logintn = (Button) findViewById(R.id.login_btn);
        RegisterText = (TextView) findViewById(R.id.register_acc);
        RegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        logintn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if (valid()) {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean valid() {
        if (email.equals("")){
            return false;
        }
        if (password.equals("")){
            return false;
        }
        return true;
    }
}