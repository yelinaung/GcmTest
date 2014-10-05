package com.yelinaung.gcmtest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;

public class MyActivity extends Activity {

  private GoogleCloudMessaging gcm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);

    gcm = GoogleCloudMessaging.getInstance(this);
    new RegisterGCMTask().execute();

    if (getIntent().getExtras() != null) {
      Log.i("extra", "extra -> " + getIntent().getExtras().get("meow"));
    }
  }

  @Override protected void onResume() {
    super.onResume();
    Log.i("onResume", getClass().getSimpleName());
  }

  private class RegisterGCMTask extends AsyncTask<Void, Void, String> {

    @Override protected void onPreExecute() {
      super.onPreExecute();
      Log.i("pre", "registering ..");
    }

    @Override protected String doInBackground(Void... params) {
      String msg;
      try {
        if (gcm == null) {
          gcm = GoogleCloudMessaging.getInstance(getBaseContext());
        }
        String SENDER_ID = "592383644034";
        String regId = gcm.register(SENDER_ID);
        msg = "Device registered, registration ID=" + regId;

        // For this demo: we don't need to send it because the device
        // will send upstream messages to a server that echo back the
        // message using the 'from' address in the message.

        // Persist the regID - no need to register again.
      } catch (IOException ex) {
        msg = "Error :" + ex.getMessage();
        // If there is an error, don't just keep trying to register.
        // Require the user to click a button again, or perform
        // exponential back-off.
      }
      return msg;
    }

    @Override protected void onPostExecute(String s) {
      super.onPostExecute(s);
      if (s != null) {
        Log.i("GCM", s);
      }
    }
  }

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    if (intent.getExtras() != null) {
     Log.i("extra", intent.getExtras().getString("meow"));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
