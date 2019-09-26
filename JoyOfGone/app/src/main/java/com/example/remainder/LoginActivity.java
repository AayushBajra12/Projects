package com.example.aayushbajra.remainder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText vTextUsername;
    EditText vTextPassword;
    Button vButtonLogin;
    TextView vTextViewSignUp;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        db=new DatabaseHelper(this);

        vTextUsername=(EditText) findViewById(R.id.etUsername);
        vTextPassword=(EditText) findViewById(R.id.etPassword);
        vButtonLogin=(Button) findViewById(R.id.btnLogin);
        vTextViewSignUp = (TextView) findViewById(R.id.txtSignup);

        vTextViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

        vButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=vTextUsername.getText().toString().trim();
                String password=vTextPassword.getText().toString().trim();
                int userId=db.checkUser(username,password);
                if(userId>0){
                    Intent mainIntent=new Intent(LoginActivity.this,MainActivity.class);
                    mainIntent.putExtra("u_id",userId);
                    startActivity(mainIntent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
