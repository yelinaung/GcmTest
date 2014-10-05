package com.yelinaung.gcmtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Ye Lin Aung on 14/09/15.
 */
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    Log.i("onReceive", "GcmBroadcastReceiver");

    ComponentName comp =
        new ComponentName(context.getPackageName(), GcmNotificationIntentService.class.getName());
    startWakefulService(context, (intent.setComponent(comp)));
    setResultCode(Activity.RESULT_OK);
  }
}
