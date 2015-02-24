package akiniyalocts.imgurapiexample.utils;

import akiniyalocts.imgurapiexample.Constants;
import android.util.Log;

/**
 * Created by AKiniyalocts on 1/16/15.
 */
public class aLog {
  public static void w (String TAG, String msg){
    if(Constants.LOGGING) {
      if (TAG != null && msg != null)
        Log.w(TAG, msg);
    }
  }

}
