package akiniyalocts.imgurapiexample.helpers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.akiniyalocts.imgur_api.model.Image;
import com.akiniyalocts.imgur_api.model.ImgurResponse;

import java.lang.ref.WeakReference;

import akiniyalocts.imgurapiexample.R;

/**
 * Created by AKiniyalocts on 1/15/15.
 * <p/>
 * This class is just created to help with notifications, definitely not necessary.
 */
public class NotificationHelper {
    public final static String TAG = NotificationHelper.class.getSimpleName();

    private WeakReference<Context> mContext;


    public NotificationHelper(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public void createUploadingNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext.get());
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_upload);
        mBuilder.setContentTitle(mContext.get().getString(R.string.notification_progress));


        mBuilder.setColor(mContext.get().getResources().getColor(R.color.primary));

        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) mContext.get().getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(mContext.get().getString(R.string.app_name).hashCode(), mBuilder.build());

    }

    public void createUploadedNotification(ImgurResponse<Image> response) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext.get());
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_gallery);
        mBuilder.setContentTitle(mContext.get().getString(R.string.notifaction_success));

        mBuilder.setContentText(response.data.getLink());

        mBuilder.setColor(mContext.get().getResources().getColor(R.color.primary));


        Intent resultIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.data.getLink()));
        PendingIntent intent = PendingIntent.getActivity(mContext.get(), 0, resultIntent, 0);
        mBuilder.setContentIntent(intent);
        mBuilder.setAutoCancel(true);

        Intent shareIntent = new Intent(Intent.ACTION_SEND, Uri.parse(response.data.getLink()));
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, response.data.getLink());
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pIntent = PendingIntent.getActivity(mContext.get(), 0, shareIntent, 0);
        mBuilder.addAction(new NotificationCompat.Action(R.drawable.abc_ic_menu_share_mtrl_alpha,
                mContext.get().getString(R.string.notification_share_link), pIntent));

        NotificationManager mNotificationManager =
                (NotificationManager) mContext.get().getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(mContext.get().getString(R.string.app_name).hashCode(), mBuilder.build());
    }

    public void createFailedUploadNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext.get());
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        mBuilder.setContentTitle(mContext.get().getString(R.string.notification_fail));


        mBuilder.setColor(mContext.get().getResources().getColor(R.color.primary));

        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) mContext.get().getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(mContext.get().getString(R.string.app_name).hashCode(), mBuilder.build());
    }
}
