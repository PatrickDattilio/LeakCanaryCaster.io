package io.caster.dattilio.leakcanary;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    View view = findViewById(R.id.textView);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startAsyncTask();
      }
    });
  }

  @SuppressLint("StaticFieldLeak")
  void startAsyncTask() {

    // This async task is an anonymous class and therefore has a hidden reference to the outer
    // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
    // the activity instance will leak.
    new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... params) {
        // Do some slow work in background
        SystemClock.sleep(20000);
        return null;
      }
    }.execute();
  }
}
