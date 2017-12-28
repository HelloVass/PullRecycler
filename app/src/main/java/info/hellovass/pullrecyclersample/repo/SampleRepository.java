package info.hellovass.pullrecyclersample.repo;

import info.hellovass.pullrecyclersample.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/12/28.
 */

public final class SampleRepository {

  private static final List<String> DATA_SOURCE = new ArrayList<>();

  private static final int PAGE_SIZE = 10;

  static {

    generateDataSource();
  }

  public interface OnDataLoadedListener {

    void onDataLoaded(List<String> datas);
  }

  public void loadData(final int pageNum, final OnDataLoadedListener onDataLoadedListener) {

    ThreadUtil.runOnUiThread(new Runnable() {

      @Override public void run() {

        onDataLoadedListener.onDataLoaded(
            DATA_SOURCE.subList((pageNum - 1) * PAGE_SIZE, (pageNum - 1) * PAGE_SIZE + PAGE_SIZE));
      }
    }, 2 * 1000L);
  }

  private static void generateDataSource() {

    for (int i = 0; i < PAGE_SIZE * 10; i++) {

      DATA_SOURCE.add("数据 -->>>" + i);
    }
  }
}
