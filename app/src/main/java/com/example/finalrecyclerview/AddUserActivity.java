package com.example.finalrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    MyDbHelper mDatabase;
    Button add;
    Spinner edtdepart,edtrole;
    EditText edtid,edtname,editemail,edtmobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        add=findViewById(R.id.buttonAddEmployee);
        add.setOnClickListener(this);
        mDatabase=new MyDbHelper(this);
        edtid=findViewById(R.id.edtempid);
        editemail=findViewById(R.id.edtemail);
        edtname = findViewById(R.id.edtusername);
        edtmobile=findViewById(R.id.edtmobile);
        edtdepart = findViewById(R.id.spinnerDepartment);
        edtrole = findViewById(R.id.spinnerRole);


    }
    public void addTask(){
        String empid = edtid.getText().toString().trim();
        String name = edtname.getText().toString().trim();
        String email = editemail.getText().toString().trim();
        String mobile = edtmobile.getText().toString().trim();
        String depart = edtdepart.getSelectedItem().toString();
        String role = edtrole.getSelectedItem().toString();
        //getting the current time for joining date
        Date date = new Date();
        String mStringDate = DateFormat.getDateTimeInstance().format(date);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:a");
        String joiningDate = sdf.format(cal.getTime());
        Conta conta= new Conta(empid,name,email,mobile,
                depart,role,mStringDate);
        String result =  mDatabase.addContacts(conta);
        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
       // finish();
        //startActivity(getIntent());
        startActivity(new Intent(AddUserActivity.this,MainActivity.class));


    }

    @Override
    public void onClick(View view) {
        addTask();
    }
    public void editTaskDialog(Conta contacts){

        if(contacts != null){
            edtid.setText(contacts.getName());
          //  edtname.setText(String.valueOf(contacts.getPhno()));
            edtmobile.setText(contacts.getName());
         //   edtdepart.se(contacts.getName());
          //  edtrole.setText(contacts.getName());
          //  ed.setText(contacts.getName());
            edtid.setText(contacts.getName());
            edtid.setText(contacts.getName());
        }

    }
}
