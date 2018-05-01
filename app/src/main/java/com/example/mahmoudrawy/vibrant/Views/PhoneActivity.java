package com.example.mahmoudrawy.vibrant.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmoudrawy.vibrant.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import instance.Patient;
import util.Phone;
import util.PhoneVerification;
import util.ViewsUtilities;

public class PhoneActivity extends AppCompatActivity {
    private EditText phoneNumber;
    private ProgressDialog progressDialog;
    private Button register;

    private static String mVerificationId;
    private static PhoneAuthProvider.ForceResendingToken mResendToken;
    private static PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        ActionBar actionBar = getSupportActionBar();
        ViewsUtilities.setActivityActionBar(actionBar, "Phone Number");
        Intent intent=getIntent();
        final Bundle bundle=intent.getBundleExtra("user_bundle");
        final Patient patient= (Patient) bundle.getSerializable("patient");
        phoneNumber= (EditText) findViewById(R.id.ET_phoneNumber);
        register= (Button) findViewById(R.id.BT_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone=phoneNumber.getText().toString();
                if(PhoneVerification.isConnected(getApplicationContext())) {
                    if (Phone.checkPhoneNumberInput(phoneNumber, phone)) {
                            progressDialog=new ProgressDialog(PhoneActivity.this);
                            progressDialog.setMessage("Verifying your Number. please wait...");
                            progressDialog.show();
                            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential credential) {
                                }

                                @Override
                                public void onCodeAutoRetrievalTimeOut(String s) {
                                    Toast.makeText(getApplicationContext(),
                                            "Connection Time out, Please Check your Connection and retry again",
                                            Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    progressDialog.dismiss();


                                    if (e instanceof FirebaseAuthInvalidCredentialsException) {

                                    } else if (e instanceof FirebaseTooManyRequestsException) {


                                    }


                                }

                                @Override
                                public void onCodeSent(String verificationId,
                                                       PhoneAuthProvider.ForceResendingToken token) {
                                  ;

                                    mVerificationId = verificationId;
                                    mResendToken = token;
                                    progressDialog.dismiss();
                                    patient.setPhoneNumber(phone);
                                    Intent intent = new Intent(PhoneActivity.this, VerificationActivity.class);
                                    bundle.putString("vId",mVerificationId);
                                    intent.putExtra("user1",bundle);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.activity_enter, R.anim.activity_exitt);
                                    finish();
                                    Log.e("sent",verificationId);

                                }
                            };
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+2"+phone,        // Phone number to verify
                                PhoneVerification.PHONEVERIFICATIONTIMEOUT,                 // Timeout duration
                                TimeUnit.SECONDS,   // Unit of timeout
                                PhoneActivity.this,               // Activity (for callback binding)
                                mCallbacks);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "There is No Connection, please check your connection",
                            Toast.LENGTH_LONG).show();
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
}
