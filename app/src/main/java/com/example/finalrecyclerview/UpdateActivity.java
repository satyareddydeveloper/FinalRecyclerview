package com.example.finalrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {
    Button update;
    MyDbHelper mDatabase;
    private ArrayList<Conta> listContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mDatabase= new MyDbHelper(this);
        findViewById(R.id.btnupdateemployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //editTaskDialog();

            }
        });
    }
    private void editTaskDialog(final Conta contacts){

        final Spinner spinner1= findViewById(R.id.spinnerDepartment);
        final Spinner spinner2= findViewById(R.id.spinnerRole);
        final EditText userid = (EditText)findViewById(R.id.edtempid);
        final EditText username = (EditText)findViewById(R.id.edtusername);
        final EditText useremail = (EditText)findViewById(R.id.edtemail);
        final EditText usermobile = (EditText)findViewById(R.id.edtmobile);
        //  final EditText department = (EditText)subView.findViewById(R.id.enter_name);
        //  final EditText role = (EditText)subView.findViewById(R.id.enter_phno);

        if(contacts != null){
            userid.setText(contacts.getEmpid());
            username.setText(contacts.getName());
            useremail.setText(contacts.getEmail());
            usermobile.setText(contacts.getMobile());

        }


        final String uid = userid.getText().toString();
        final String uname = username.getText().toString();

        final String uemail = useremail.getText().toString();
        final String umobile = usermobile.getText().toString();
        String dept = spinner1.getSelectedItem().toString();
        String role = spinner2.getSelectedItem().toString();


        Date date = new Date();
        String mStringDate = DateFormat.getDateTimeInstance().format(date);

        if(TextUtils.isEmpty(uid)||TextUtils.isEmpty(uname)||TextUtils.isEmpty(uemail)||TextUtils.isEmpty(umobile)){
            Toast.makeText(getApplicationContext(), "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
        }
        else{
            mDatabase.updateContacts(new Conta(contacts.getId(), uid,
                    uname,uemail,umobile,dept,role,mStringDate));
            startActivity(new Intent(this,AddUserActivity.class));
            //refresh the activity
            //  ((Activity)context).finish();
            // context.startActivity(((Activity)context).getIntent());
        }
    }
}
