package util;

import android.support.v7.app.ActionBar;

/**
 * Created by PH-Data™ 01221240053 on 24/02/2018.
 */

public class ViewsUtilities {
    public static void setActivityActionBar(ActionBar activityActionBar,String barTitle){
        activityActionBar.setDisplayHomeAsUpEnabled(true);
        activityActionBar.setTitle(barTitle);
    }
}
