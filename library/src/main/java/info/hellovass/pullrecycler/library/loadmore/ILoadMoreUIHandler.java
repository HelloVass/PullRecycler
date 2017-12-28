package info.hellovass.pullrecycler.library.loadmore;

import android.view.View;

/**
 * Created by hello on 2017/3/11.
 */

public interface ILoadMoreUIHandler {

  void onLoading();

  void onLoadSucceed(boolean hasMore);

  void onLoadFailed(String error);

  View getConvertView();
}
