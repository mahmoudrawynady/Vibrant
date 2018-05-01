package util;

import android.widget.EditText;

/**
 * Created by PH-Data™ 01221240053 on 24/02/2018.
 */

public class Phone {

    public static boolean checkPhoneNumberInput(EditText editText, String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            editText.setError("Please Enter a Number");
            return false;
        } else if (phoneNumber.length() > 11 || phoneNumber.length() < 11) {
            editText.setError("please Enter a Valid Number");
            return false;
        } else if (phoneNumber.regionMatches(0, "010", 0, 3) != true &&
                phoneNumber.regionMatches(0, "011", 0, 3) != true &&
                phoneNumber.toString().regionMatches(0, "012", 0, 3) != true
                && phoneNumber.regionMatches(0, "015", 0, 3) != true) {

            editText.setError("please Enter a Valid Number");
            return false;


        }
        else
            return true;
    }
}
