package com.example.mahmoudrawy.vibrant.Views;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmoudrawy.vibrant.R;
import com.google.firebase.auth.FirebaseAuth;

import util.Phone;

public class Login extends AppCompatActivity {
    Button login;
    Button create;
    EditText phoneLogin;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        login= (Button) findViewById(R.id.BT_login);
        create= (Button) findViewById(R.id.BT_create);
        firebaseAuth=FirebaseAuth.getInstance();
        phoneLogin= (EditText) findViewById(R.id.ET_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Phone.checkPhoneNumberInput(phoneLogin,phoneLogin.getText().toString())) {
                    if (firebaseAuth.getCurrentUser() != null) {
                        Toast.makeText(getApplicationContext(), "logedin.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this, Home.class));
                        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exitt);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "please enter a valid Number or create new Account.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, RegisterActivity.class));
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exitt);
            }
        });
    }
}
