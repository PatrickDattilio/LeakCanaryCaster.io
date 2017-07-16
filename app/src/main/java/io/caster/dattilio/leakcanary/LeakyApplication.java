package io.caster.dattilio.leakcanary;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class LeakyApplication extends Application {

  RefWatcher refwatcher;

  @Override
  public void onCreate() {
    super.onCreate();
    if (LeakCanary.isInAnalyzerProcess(this)){
      return;
    }
    refwatcher = LeakCanary.install(this);
  }
}
