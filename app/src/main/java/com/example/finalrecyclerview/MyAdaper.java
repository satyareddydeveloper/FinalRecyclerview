package com.example.finalrecyclerview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyAdaper extends RecyclerView.Adapter<MyAdaper.ContactViewHolder> implements Filterable {


    private Context context;
    private ArrayList<Conta> listContacts;
    private ArrayList<Conta> mArrayList;

    private MyDbHelper mDatabase;

    public MyAdaper(Context context, ArrayList<Conta> listContacts) {
        this.context = context;
        this.listContacts = listContacts;
        this.mArrayList=listContacts;
        mDatabase = new MyDbHelper(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displayusercard, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactViewHolder holder, int position) {
        final Conta contacts = listContacts.get(position);


        holder.empid.setText(contacts.getEmpid());
        holder.name.setText(contacts.getName());
        holder.email.setText(contacts.getEmail());
        holder.mobile.setText(contacts.getMobile());
        holder.depart.setText(contacts.getDepatment());
        holder.role.setText(contacts.getRole());
        holder.joing.setText(contacts.getJoiningdate());
        holder.editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(contacts);
               // Intent intent = new Intent(view.getContext(), AddUserActivity.class);

//start the activity from the view/context
                //view.getContext().startActivity(intent); //I
               // editTaskDialog(contacts);

            }
        });
        holder.deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletedialog(contacts);
                //refresh the activity page.

            }
        });
    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence
                                                             charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    listContacts = mArrayList;
                } else {

                    ArrayList<Conta> filteredList = new ArrayList<>();

                    for (Conta contacts : mArrayList) {

                        if (contacts.getName().toLowerCase().contains(charString)) {

                            filteredList.add(contacts);
                        }
                    }

                    listContacts = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listContacts;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listContacts = (ArrayList<Conta>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView empid,name,email,mobile,depart,role,joing;
        public ImageView deleteContact;
        public  ImageView editContact;

        public ContactViewHolder(View itemView) {
            super(itemView);
            empid = (TextView)itemView.findViewById(R.id.tv_employee_id);
            name = (TextView)itemView.findViewById(R.id.tv_username);
            email = (TextView)itemView.findViewById(R.id.tv_Email);
            mobile = (TextView)itemView.findViewById(R.id.tv_mobileno);
            depart = (TextView)itemView.findViewById(R.id.tv_department);
            role = (TextView)itemView.findViewById(R.id.tv_role);
            joing = (TextView)itemView.findViewById(R.id.tv_joiningdate);
            deleteContact = (ImageView)itemView.findViewById(R.id.delete_contact);
            editContact = (ImageView)itemView.findViewById(R.id.edit_contact);
        }
    }
    private void editTaskDialog(final Conta contacts){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_contact_layout, null);
        final Spinner spinner1= subView.findViewById(R.id.spinnerDepartment);
        final Spinner spinner2= subView.findViewById(R.id.spinnerRole);
        final EditText userid = (EditText)subView.findViewById(R.id.edtempid);
        final EditText username = (EditText)subView.findViewById(R.id.edtusername);
        final EditText useremail = (EditText)subView.findViewById(R.id.edtemail);
        final EditText usermobile = (EditText)subView.findViewById(R.id.edtmobile);
        final Spinner department = subView.findViewById(R.id.spinnerDepartment);
       final Spinner role = subView.findViewById(R.id.spinnerRole);

        if(contacts != null){
            userid.setText(contacts.getEmpid());
            username.setText(contacts.getName());
            useremail.setText(contacts.getEmail());
            usermobile.setText(contacts.getMobile());


            String compareValue = contacts.getDepatment();
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.departments, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            department.setAdapter(adapter);
            if (compareValue != null) {
                int spinnerPosition = adapter.getPosition(compareValue);
                department.setSelection(spinnerPosition);
            }
////////////////////////////
            String rolecompare = contacts.getRole();
            ArrayAdapter<CharSequence> roleadapter = ArrayAdapter.createFromResource(context, R.array.Role, android.R.layout.simple_spinner_item);
            roleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(roleadapter);
            if (rolecompare != null) {
                int spinnerPosition = roleadapter.getPosition(rolecompare);
                role.setSelection(spinnerPosition);
            }

           // ArrayAdapter<CharSequence> arrayAdapter =ArrayAdapter.createFromResource(this,R.array.departments,android.R.layout.simple_list_item_1);
           // arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           // sp.setAdapter(arrayAdapter);

            //////////
           // ArrayAdapter<String> adapter = (ArrayAdapter<String>) department.getAdapter();
          //  int position = adapter.getPosition(contacts.getDepatment());
          //  department.setSelection(position);
            //////////////
         // spinner1.setSelection(((ArrayAdapter<String>)spinner1.getAdapter()).getPosition(contacts.getDepatment()));
          //  ArrayAdapter<String> adapter2 = (ArrayAdapter<String>) role.getAdapter();
          //  int position2 = adapter2.getPosition(contacts.getDepatment());
         //   department.setSelection(position2);


        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Employee");

        builder.setView(subView);
        builder.create();

            builder.setPositiveButton("EDIT EMPLOYEE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String uid = userid.getText().toString();
                final String uname = username.getText().toString();

                final String uemail = useremail.getText().toString();
                final String umobile = usermobile.getText().toString();
                String dept = spinner1.getSelectedItem().toString();
                String role = spinner2.getSelectedItem().toString();


                Date date = new Date();
                String mStringDate = DateFormat.getDateTimeInstance().format(date);

                if(TextUtils.isEmpty(uid)||TextUtils.isEmpty(uname)||TextUtils.isEmpty(uemail)||TextUtils.isEmpty(umobile)){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    mDatabase.updateContacts(new Conta(contacts.getEmpid(),
                            uname,uemail,umobile,dept,role));

                    //refresh the activity
                   ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    private void deletedialog(final Conta contacts){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are You Sure Want to Delete ");
        builder.create();
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                if(contacts != null){
                    mDatabase.deleteContact(contacts.getEmpid());
                    ((Activity)context).finish();
                    context.startActivity(((Activity) context).getIntent());
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();

            }

        });
        builder.show();







    }




}

