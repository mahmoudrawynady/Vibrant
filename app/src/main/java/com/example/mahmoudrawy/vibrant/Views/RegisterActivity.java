package com.example.mahmoudrawy.vibrant.Views;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.mahmoudrawy.vibrant.R;

import instance.Patient;
import util.SinUp;
import util.ViewsUtilities;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstName, lastName, cityName, userAddress;
    private Button continueRegister;
    private RadioButton adult, children;
    private Patient patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        ViewsUtilities.setActivityActionBar(actionBar, "Register");
        patient = new Patient();
        firstName = (EditText) findViewById(R.id.ET_firstName);
        lastName = (EditText) findViewById(R.id.ET_lastName);
        cityName = (EditText) findViewById(R.id.ET_city);
        userAddress = (EditText) findViewById(R.id.ET_address);
        adult = (RadioButton) findViewById(R.id.RB_adult);
        children = (RadioButton) findViewById(R.id.RB_children);
        continueRegister = (Button) findViewById(R.id.BT_continueRegister);
        continueRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();
                String city = cityName.getText().toString();
                String userStreet = userAddress.getText().toString();
                if(fillUserInfo(first, last, city, userStreet)) {
                    Intent intent = new Intent(RegisterActivity.this, PhoneActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("patient", patient);
                    intent.putExtra("user_bundle", bundle);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_enter, R.anim.activity_exitt);
                    finish();
                }

            }
        });

        children.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    patient.setChildren(true);
            }
        });
        adult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    patient.setAdult(true);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean fillUserInfo(String first, String last, String city, String userStreet) {
        if (!SinUp.checkInputData(first)) {
            SinUp.displayInputErrorMessage(firstName, "Please Enter your First Name");
            return false;
        }
        if (!SinUp.checkInputData(last)) {
            SinUp.displayInputErrorMessage(lastName, "Please Enter your Last Name");
            return false;
        }
        if (!SinUp.checkInputData(city)) {
            SinUp.displayInputErrorMessage(cityName, "Please Enter your City Name");
            return false;
        }
        if (!SinUp.checkInputData(userStreet)) {
            SinUp.displayInputErrorMessage(userAddress, "Please Enter your Address");
            return false;
        }

        patient.setName(first+" "+last);
        patient.setCountry("Egypt");
        patient.setStreet(userStreet);
        patient.setCity(city);
        return true;
    }

}
