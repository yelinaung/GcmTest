package com.yelinaung.gcmtest;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by Ye Lin Aung on 14/09/15.
 */
public class GcmNotificationIntentService extends IntentService {
  public static final int NOTIFICATION_ID = 1;
  private static final String MESSAGE_KEY = "msg";

  public GcmNotificationIntentService() {
    super("GcmIntentService");
  }

  public static final String TAG = "GCMNotificationIntentService";

  @Override
  protected void onHandleIntent(Intent intent) {
    Bundle extras = intent.getExtras();
    GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

    String messageType = gcm.getMessageType(intent);

    if (!extras.isEmpty()) {
      if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
        sendNotification("Send error: " + extras.toString());
      } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
        sendNotification("Deleted messages on server: " + extras.toString());
      } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
        sendNotification("" + extras.get(MESSAGE_KEY));
        Log.i(TAG, "Received: " + extras.toString());
        new MyDownloadStuff().execute();
      }
    }
    GcmBroadcastReceiver.completeWakefulIntent(intent);
  }

  private void sendNotification(String msg) {
    Log.d(TAG, "Preparing to send notification...: " + msg);

    String[] msgArray = msg.split(",");

    NotificationManager mNotificationManager =
        (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

    Intent myIntent = new Intent(this, MyActivity.class);
    myIntent.putExtra("meow", "this is a meow~");
    myIntent.putExtra("dl", true);

    PendingIntent contentIntent =
        PendingIntent.getActivity(this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle(msgArray[0])
            .setContentText(msgArray[1].replaceFirst("\\s", ""));

    mBuilder.setContentIntent(contentIntent);
    mBuilder.setAutoCancel(true);

    mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    Log.d(TAG, "Notification sent successfully.");
  }
}
