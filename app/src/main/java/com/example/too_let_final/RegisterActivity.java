package com.example.too_let_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextName,editTextEmail,editTextPhone,editTextPassword;
    TextView Textlogin;
    String textName,textEmail,textPhone,textPassword;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("allUser");
        mAuth = FirebaseAuth.getInstance();

        editTextName = findViewById(R.id.name_);
        editTextEmail = findViewById(R.id.email_);
        editTextPhone = findViewById(R.id.phone_);
        editTextPassword = findViewById(R.id.password_);
        Textlogin = (TextView) findViewById(R.id.login_screen);
        Textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

    }

    public void register(View view) {

        textName = editTextName.getText().toString();
        textEmail= editTextEmail.getText().toString();
        textPhone = editTextPhone.getText().toString();
        textPassword = editTextPassword.getText().toString();
        if (valid()){
            Userinfo userinfo = new Userinfo(textName,textEmail,textPhone,textPassword);

            mAuth.createUserWithEmailAndPassword(textEmail,textPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userinfo)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(RegisterActivity.this, "User signup successfully Please Go to Login Page", Toast.LENGTH_LONG).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this,e.getMessage()+ "", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            Toast.makeText(this, "All fields must required", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean valid() {

        if (textName.equals("")){
            return false;
        }
        if (textEmail.equals("")){
            return false;
        }
        if (textPhone.equals("")){
            return false;
        }
        if (textPassword.equals("")){
            return false;
        }
        return true;
    }

}