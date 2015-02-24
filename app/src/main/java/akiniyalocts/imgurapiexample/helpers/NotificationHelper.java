package akiniyalocts.imgurapiexample.helpers;

import akiniyalocts.imgurapiexample.R;
import akiniyalocts.imgurapiexample.imgurmodel.ImageResponse;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by AKiniyalocts on 1/15/15.
 *
 * This class is just created to help with notifications, definitely not necessary.
 */
public class NotificationHelper {
  public final static String TAG = NotificationHelper.class.getSimpleName();

  private Context mContext;


  public NotificationHelper(Context mContext) {
    this.mContext = mContext;
  }



  public void createUploadingNotification(){
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
    mBuilder.setSmallIcon(android.R.drawable.ic_menu_upload);
    mBuilder.setContentTitle("Uploading image...");


    mBuilder.setColor(mContext.getResources().getColor(R.color.primary));

    mBuilder.setAutoCancel(true);

    NotificationManager mNotificationManager =
        (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

    mNotificationManager.notify(mContext.getString(R.string.app_name).hashCode(), mBuilder.build());

  }
  public void createUploadedNotification(ImageResponse response){
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
    mBuilder.setSmallIcon(android.R.drawable.ic_menu_gallery);
    mBuilder.setContentTitle("Successfully uploaded ");

    mBuilder.setContentText(response.data.link);

    mBuilder.setColor(mContext.getResources().getColor(R.color.primary));


    Intent resultIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.data.link));
    PendingIntent intent = PendingIntent.getActivity(mContext, 0, resultIntent, 0);
    mBuilder.setContentIntent(intent);
    mBuilder.setAutoCancel(true);

    Intent shareIntent = new Intent(Intent.ACTION_SEND, Uri.parse(response.data.link));
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_TEXT, response.data.link);
    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, shareIntent, 0);
    mBuilder.addAction(new NotificationCompat.Action(R.drawable.abc_ic_menu_share_mtrl_alpha,"Share link", pIntent));

    NotificationManager mNotificationManager =
        (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

    mNotificationManager.notify(mContext.getString(R.string.app_name).hashCode(), mBuilder.build());
  }
  public void createFailedUploadNotification(){
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
    mBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
    mBuilder.setContentTitle("Image failed to upload...");


    mBuilder.setColor(mContext.getResources().getColor(R.color.primary));

    mBuilder.setAutoCancel(true);

    NotificationManager mNotificationManager =
        (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

    mNotificationManager.notify(mContext.getString(R.string.app_name).hashCode(), mBuilder.build());
  }



}
