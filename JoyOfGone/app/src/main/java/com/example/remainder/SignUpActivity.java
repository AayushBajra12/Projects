package com.example.aayushbajra.remainder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText vTextUsername;
    EditText vTextPassword;
    EditText vTextConfirmPassword;
    EditText vTextEmail;
    Button vButtonSignUp;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db=new DatabaseHelper(this);

        vTextUsername=(EditText) findViewById(R.id.etUsername);
        vTextPassword=(EditText) findViewById(R.id.etPassword);
        vTextConfirmPassword=(EditText) findViewById(R.id.etConfirmPassword);
        vTextEmail=(EditText)findViewById(R.id.etEmail);
        vButtonSignUp=(Button) findViewById(R.id.btnSignUp);

        vButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= vTextUsername.getText().toString().trim();
                String password= vTextPassword.getText().toString().trim();
                String confirmPassword= vTextConfirmPassword.getText().toString().trim();
                String email=vTextEmail.getText().toString().trim();
                if(password.equals(confirmPassword)){
                    long id=db.addUser(username,password,email);
                    if(id>0) {

                        Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        Toast.makeText(SignUpActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SignUpActivity.this,"Username Already used",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
