package com.example.finalrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button add;
    TextView showemployee;
    Spinner edtdepart,edtrole;
    EditText edtid,edtname,editemail,edtmobile;
    MyDbHelper mDatabase;


    /////
    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Conta> allContacts=new ArrayList<>();
    private MyAdaper mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase= new MyDbHelper(this);

        //////////////////////////
       shoodata();
        //////////////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddUserActivity.class));

//addTaskDialog();
            }
        });
    }


    public void shoodata(){
        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);

        RecyclerView contactView = (RecyclerView)findViewById(R.id.product_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);
        mDatabase = new MyDbHelper(this);
        allContacts = mDatabase.listContacts();

        if(allContacts.size() > 0){
            contactView.setVisibility(View.VISIBLE);
            mAdapter = new MyAdaper(this, allContacts);
            contactView.setAdapter(mAdapter);

        }else {
            contactView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no contact in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
    }
    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_contact_layout, null);

        edtid=findViewById(R.id.edtempid);
        editemail=findViewById(R.id.edtemail);
        edtname = findViewById(R.id.edtusername);
        edtmobile=findViewById(R.id.edtmobile);
        edtdepart = findViewById(R.id.spinnerDepartment);
        edtrole = findViewById(R.id.spinnerRole);
  //      startActivity(new Intent(AddUserActivity.this,MainActivity.class));
//

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new CONTACT");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADD CONTACT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{

                    Conta conta= new Conta(empid,name,email,mobile,
                            depart,role,mStringDate);
                    String result =  mDatabase.addContacts(conta);
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                    // finish();
                    //startActivity(getIntent());
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mynemu, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }
    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mAdapter!=null)
                    mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
