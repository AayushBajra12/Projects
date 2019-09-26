package com.example.aayushbajra.remainder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnApntNew;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent receivedIntent=getIntent();
        userId=receivedIntent.getIntExtra("u_id",-1);
        btnApntNew = (Button) findViewById(R.id.btnApntNew);
        btnApntNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked New Button");
                //Toast.makeText(MainActivity.this, "New Appointment Created", Toast.LENGTH_SHORT).show();
                Intent addAppointmentIntent=new Intent(MainActivity.this,addAppointmentActivity.class);
                addAppointmentIntent.putExtra("u_id",userId);
                startActivity(addAppointmentIntent);
            }
        });
    }
}
