package info.hellovass.pullrecyclersample.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by hello on 2017/12/28.
 */

public final class ThreadUtil {

  private static final Handler UI_HANDLER = new Handler(Looper.getMainLooper());

  private ThreadUtil() {

  }

  public static void runOnUiThread(Runnable runnable,long delayMillis){

    UI_HANDLER.postDelayed(runnable,delayMillis);
  }
}
