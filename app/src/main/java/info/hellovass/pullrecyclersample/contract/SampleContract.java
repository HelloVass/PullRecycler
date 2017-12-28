package info.hellovass.pullrecyclersample.contract;

import java.util.List;

/**
 * Created by hello on 2017/12/28.
 */

public interface SampleContract {

  interface View {

    void onInitPageSucceed(boolean hasMore);

    void onInitPageFailed(String error);

    void onRefreshPageSucceed(boolean hasMore);

    void onRefreshPageFailed(String error);

    void onLoadNextPageSucceed(int positionStart, int size, boolean hasMore);

    void onLoadNextPageFailed(String error);
  }

  interface Presenter {

    List<String> getDataSource();

    void initPage();

    void refreshPage();

    void loadNextPage();
  }
}
