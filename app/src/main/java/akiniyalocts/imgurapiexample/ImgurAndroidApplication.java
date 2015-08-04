package akiniyalocts.imgurapiexample;

import android.app.Application;

import com.akiniyalocts.imgur_api.ImgurClient;

/**
 * Created by anthony on 8/4/15.
 */
public class ImgurAndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImgurClient.initialize(Constants.MY_IMGUR_CLIENT_ID);
    }
}
