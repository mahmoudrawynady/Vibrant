package util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


/**
 * Created by PH-Data™ 01221240053 on 24/02/2018.
 */

public class PhoneVerification {

    public final static int PHONEVERIFICATIONTIMEOUT = 60;
    private static String mVerificationId;
    private static PhoneAuthProvider.ForceResendingToken mResendToken;
    private static PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private static Context context;
    private static ProgressDialog progressDialog;
    private static boolean codeState;


    public static void sendVerificationRequest(String phoneNumber, AppCompatActivity appCompatActivity) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+2" + phoneNumber,        // Phone number to verify
                PhoneVerification.PHONEVERIFICATIONTIMEOUT,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                appCompatActivity,               // Activity (for callback binding)
                mCallbacks);

    }

    public static String createVerificationCallBacks(final Context context, final AppCompatActivity appCompatActivity) {
        PhoneVerification.context = context;
        progressDialog = new ProgressDialog(appCompatActivity);
        progressDialog.setMessage("loading. please wait...");
        progressDialog.show();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.e("faild", "faild");
                progressDialog.dismiss();


                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                } else if (e instanceof FirebaseTooManyRequestsException) {


                }


            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.e("sent", "sent");

                mVerificationId = verificationId;
                mResendToken = token;
                progressDialog.dismiss();
                Log.e("mahmoudrawynady", mVerificationId + " " + mResendToken);


            }
        };
        return mVerificationId;


    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


}

