package com.example.finalrecyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDbHelper extends SQLiteOpenHelper {
    private	static final int DATABASE_VERSION =	6;
    private	static final String	DATABASE_NAME = "kayal";
    private	static final String TABLE_CONTACTS = "sateeshcontact";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMPID = "empid";
    private static final String COLUMN_NAME = "contactname";

    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NO = "phno";

    private static final String COLUMN_DEPART = "department";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_Joiningdate = "joingdate";



    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_CONTACTS_TABLE = "CREATE	TABLE " + TABLE_CONTACTS + "" +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_EMPID + " TEXT," +
                COLUMN_NAME + " text," +COLUMN_EMAIL+" text, "+COLUMN_NO+" text, "
                +COLUMN_DEPART+" text ,"+COLUMN_ROLE+" text, "+COLUMN_Joiningdate+" text "+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
    public String addContacts(Conta contacts){
        try
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_EMPID, contacts.getEmpid());
            values.put(COLUMN_NO, contacts.getMobile());
            values.put(COLUMN_NAME, contacts.getName());
            values.put(COLUMN_EMAIL, contacts.getEmail());
            values.put(COLUMN_DEPART, contacts.getDepatment());
            values.put(COLUMN_ROLE, contacts.getRole());
            values.put(COLUMN_Joiningdate, contacts.getJoiningdate());
            SQLiteDatabase db = this.getWritableDatabase();
           long i =  db.insert(TABLE_CONTACTS, null, values);

            return "complete "+i+" "+db.getPath();
        }catch (Exception e){
            return e.getMessage();
        }


    }
    public void updateContacts(Conta contacts){
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, contacts.getName());
        values.put(COLUMN_EMAIL,contacts.getEmail());
        values.put(COLUMN_NO, contacts.getMobile());
        values.put(COLUMN_DEPART,contacts.getDepatment());
        values.put(COLUMN_ROLE,contacts.getRole());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_CONTACTS, values, COLUMN_EMPID
                + "	= ?", new String[] { String.valueOf(contacts.getEmpid())});
    }


    public ArrayList<Conta> listContacts(){
        String sql = "select * from " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Conta> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String empid = cursor.getString(1);
                String name = cursor.getString(2);
                String email = cursor.getString(3);
                String phno = cursor.getString(4);
                String depart = cursor.getString(5);
                String role = cursor.getString(6);
                String date = cursor.getString(7);
                storeContacts.add(new Conta(empid, name, email,phno,depart,role,date));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }
        public void deleteContact(String id) {
            try {

                SQLiteDatabase db = this.getWritableDatabase();
                db.delete(TABLE_CONTACTS, COLUMN_EMPID + "	= ?", new String[]{String.valueOf(id)});


            } catch (Exception e) {
                e.getMessage();
            }
        }
    public Conta findContacts(String name){
        String query = "Select * FROM "	+ TABLE_CONTACTS + " WHERE " +
                COLUMN_NAME + " = " + "name";
        SQLiteDatabase db = this.getWritableDatabase();
        Conta contacts = null;
        Cursor cursor = db.rawQuery(query,	null);
        if	(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String empid = cursor.getString(1);
            String names = cursor.getString(2);
            String emails = cursor.getString(3);
            String mobiles = cursor.getString(4);
            String departs = cursor.getString(5);
            String roles = cursor.getString(6);
            String joiningdate = cursor.getString(7);
            contacts = new Conta(id, empid, names,emails,mobiles,departs,roles,joiningdate);
        }
        cursor.close();
        return contacts;
    }

}

