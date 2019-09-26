package com.example.aayushbajra.remainder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class addAppointmentActivity extends AppCompatActivity {

    EditText vItemDescription;
    EditText vAdress;
    EditText vPhone;
    DatePicker vDate;
    TimePicker vTime;
    Button vAddAppointment;

    int userId;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        db=new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        userId=receivedIntent.getIntExtra("u_id",-1);
        System.out.println("add appointment userID="+userId);

        vItemDescription=(EditText)findViewById(R.id.etItemDescription);
        vAdress=(EditText)findViewById(R.id.etAdress);
        vPhone=(EditText)findViewById(R.id.etPhone);
        vDate=(DatePicker)findViewById(R.id.datePicker);
        vTime=(TimePicker)findViewById(R.id.timePicker);
        vAddAppointment=(Button)findViewById(R.id.btnAddAppointment);
        //vTime.setIs24HourView(true);

        vAddAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("Add Appointment Button Clicked."+vTime.getCurrentHour()+":"+vTime.getCurrentMinute());
                //System.out.println("Add Appointment Button Clicked."+vDate.getDayOfMonth()+":"+vDate.getMonth()+":"+vDate.getYear());
                int day=vDate.getDayOfMonth();
                int month=vDate.getMonth()+1;
                int year=vDate.getYear();

                int hour=vTime.getCurrentHour();
                int minute=vTime.getCurrentMinute();

                String itemDescription=vItemDescription.getText().toString().trim();
                String address=vAdress.getText().toString().trim();
                String phone=vPhone.getText().toString().trim();

                String date=day+"."+month+"."+year;
                String amOrPm="AM";
                if(hour>=12){
                    amOrPm="PM";
                    hour-=12;
                }
                if(hour==0)hour=12;
                String minuteText=Integer.toString(minute);
                if(minuteText.length()==1)minuteText="0"+minuteText;
                String time=hour+":"+minuteText+" "+amOrPm;
                //Toast.makeText(addAppointmentActivity.this,day+":"+month+":"+year+"  "+hour+":"+minute,Toast.LENGTH_SHORT).show();
                long appointmentiId=db.addAppointment(userId,itemDescription,address,phone,date,time);
                Toast.makeText(addAppointmentActivity.this,userId+" "+appointmentiId+" "+date+"  "+time,Toast.LENGTH_SHORT).show();

                //Intent signUpIntent=new Intent(LoginActivity.this,SignUpActivity.class);
                //startActivity(signUpIntent);
            }
        });



    }
}
