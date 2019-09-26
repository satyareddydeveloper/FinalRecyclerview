package com.example.finalrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoooooginActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static TextView attempts;
    private static Button login_btn;
    int attempt_counter = 5;
    MyDbHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looooogin);
        username = (EditText)findViewById(R.id.edtusername);
        password = (EditText)findViewById(R.id.edt_password);
        attempts = (TextView)findViewById(R.id.no_of_attempts);
        login_btn = (Button)findViewById(R.id.btn_login);
        myDbHelper= new MyDbHelper(this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

            }
        });
    }
}
