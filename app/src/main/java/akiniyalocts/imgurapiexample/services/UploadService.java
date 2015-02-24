package akiniyalocts.imgurapiexample.services;

import akiniyalocts.imgurapiexample.imgurmodel.BasicResponse;
import akiniyalocts.imgurapiexample.imgurmodel.ImageResponse;
import akiniyalocts.imgurapiexample.utils.NetworkUtils;
import android.app.Activity;
import android.os.AsyncTask;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by AKiniyalocts on 1/12/15.
 */
public class UploadService extends AsyncTask<Void, Void, Void> {
  public final static String TAG = UploadService.class.getSimpleName();


  public String title, description, albumId;
  private ImageResponse response;
  private BasicResponse shareResponse;
  private Activity activity;
  private OnImageUploadedListener mUploaded;
  private ArrayList<File> images;
  private boolean isShare;


  public UploadService(ArrayList<File> images, Activity activity, String title, String description,
      String albumId, boolean isShare) {
    this.images = images;
    this.activity = activity;
    this.title = title;
    this.description = description;
    this.albumId = albumId;
    this.isShare = isShare;
    mUploaded = (OnImageUploadedListener) activity;
  }

  public UploadService(ArrayList<File> images, Activity activity, boolean isShare) {
    this.images = images;
    this.activity = activity;
    this.isShare = isShare;
    mUploaded = (OnImageUploadedListener) activity;
  }

  public UploadService(ArrayList<File> images, Activity activity, String title, boolean isShare) {
    this.images = images;
    this.activity = activity;
    this.title = title;
    this.isShare = isShare;
    mUploaded = (OnImageUploadedListener) activity;
  }

  @Override protected void onPreExecute() {
    super.onPreExecute();
  }

  @Override protected Void doInBackground(Void... params) {
    if(NetworkUtils.isConnected(activity)) {
      if (NetworkUtils.connectionReachable()) {



      }

    }
    return null;
  }

  @Override protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);

  }

}
