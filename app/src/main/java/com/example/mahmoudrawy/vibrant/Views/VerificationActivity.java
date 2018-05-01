package com.example.mahmoudrawy.vibrant.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmoudrawy.vibrant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import instance.Patient;
import util.PhoneVerification;
import util.SinUp;
import util.ViewsUtilities;

public class VerificationActivity extends AppCompatActivity {
        private Patient patient;
        private Button verify;
        private FirebaseAuth firebaseAuth;
        private EditText code;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ActionBar actionBar = getSupportActionBar();
        ViewsUtilities.setActivityActionBar(actionBar, "Phone Verification");
        Bundle bundle=getIntent().getBundleExtra("user1");
        patient = (Patient) bundle.getSerializable("patient");
        final String vId=bundle.getString("vId");
        verify= (Button) findViewById(R.id.BT_verify);
        code= (EditText) findViewById(R.id.ET_code);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verificationCode=code.getText().toString();
                if(!SinUp.checkInputData(verificationCode)){
                    SinUp.displayInputErrorMessage(code,"please Enter your Code");
                }
                else {
                    if(PhoneVerification.isConnected(getApplicationContext())) {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(vId, verificationCode);
                        signInWithPhoneAuthCredential(credential, firebaseAuth);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"There is no Connection, please check your Interner Connection",
                                Toast.LENGTH_LONG).show();
                    }
                }

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

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, FirebaseAuth firebaseAuth) {
        progressDialog = new ProgressDialog(VerificationActivity.this);
        progressDialog.setMessage("Verifying your Code. please wait...");
        progressDialog.show();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerificationActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            progressDialog.dismiss();
                            databaseReference.child("Patient").child(user.getUid()).setValue(patient);
                            Toast.makeText(getApplicationContext(),"Congratulations your Account was registered",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(VerificationActivity.this, Home.class));
                            overridePendingTransition(R.anim.activity_enter, R.anim.activity_exitt);
                            finish();




                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Invalid Code, please enter a valid Code",Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });



    }

}
