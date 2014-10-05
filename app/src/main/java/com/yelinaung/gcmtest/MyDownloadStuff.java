package com.yelinaung.gcmtest;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Ye Lin Aung on 14/09/17.
 */
public class MyDownloadStuff extends AsyncTask<Void, Void, Void> {

  public MyDownloadStuff() {
  }

  @Override protected void onPreExecute() {
    super.onPreExecute();
  }

  @Override protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
  }

  @Override protected Void doInBackground(Void... params) {

    for (int i = 0; i <= 10; i++) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Log.i("job", "downloading " + i + " file");
    }
    return null;
  }
}
