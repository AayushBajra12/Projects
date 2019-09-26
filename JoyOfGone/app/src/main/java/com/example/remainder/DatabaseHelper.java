package com.example.aayushbajra.remainder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="remainder.db";
    public static final String TABLE_NAME="registerusers";
    public static final String COL_1="ID";
    public static final String COL_2="username";
    public static final String COL_3="password";

    public static final String APPOINTMENT_TABLE="appointment";
    public static final String ACOL_1="ID";
    public static final String ACOL_2="userid";
    public static final String ACOL_3="item_description";
    public static final String ACOL_4="adress";
    public static final String ACOL_5="phone";
    public static final String ACOL_6="date";
    public static final String ACOL_7="time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registerusers(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT unique, password TEXT, email TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE "+APPOINTMENT_TABLE+" ("+ACOL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ACOL_2+" INTEGER, "
                +ACOL_3+" TEXT, "+ACOL_4+" TEXT, "+ACOL_5+" TEXT, "+ACOL_6+" TEXT, "+ACOL_7+" TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+APPOINTMENT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String username, String password,String email){

        SQLiteDatabase db=this.getWritableDatabase();
        //onUpgrade(db,1,1);

        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("email",email);
        long id= db.insert("registerusers",null,contentValues);
        db.close();
        return id;

    }

    public int checkUser(String username,String password){
        String[] columns={COL_1};
        SQLiteDatabase db=getReadableDatabase();
        String selection=COL_2+"=? and "+COL_3+"=?";
        String[] selectionArgs={username,password};
        Cursor cursor =db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count=cursor.getCount();
        cursor.close();
        return count;
        /*
        if(count>0)
            return true;
        else
            return false;
       */
    }

    public long addAppointment(int userId, String itemDescription, String address, String phone, String date, String time){

        SQLiteDatabase db=this.getWritableDatabase();
        //onUpgrade(db,1,1);

        ContentValues contentValues=new ContentValues();
        contentValues.put(ACOL_2,userId);
        contentValues.put(ACOL_3,itemDescription);
        contentValues.put(ACOL_4,address);
        contentValues.put(ACOL_5,phone);
        contentValues.put(ACOL_6,date);
        contentValues.put(ACOL_7,time);
        long id= db.insert(APPOINTMENT_TABLE,null,contentValues);
        db.close();
        return id;

    }
}
