package util;

import android.widget.EditText;

/**
 * Created by PH-Data™ 01221240053 on 24/02/2018.
 */

public class SinUp {
    public static boolean checkInputData(String inputData){
        if (inputData.isEmpty()|| inputData.length()<4)
            return false;
        return true;
    }

    public static void displayInputErrorMessage(EditText editText, String message){
        editText.setError(message);
    }

}
