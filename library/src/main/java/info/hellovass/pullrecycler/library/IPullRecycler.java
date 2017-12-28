package info.hellovass.pullrecycler.library;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import info.hellovass.pullrecycler.library.layoutmanager.ILayoutManager;
import info.hellovass.pullrecycler.library.loadmore.ILoadMoreHandler;
import info.hellovass.pullrecycler.library.loadmore.ILoadMoreUIHandler;
import info.hellovass.pullrecycler.library.refresh.IRefreshHandler;

/**
 * Created by hello on 2017/12/27.
 *
 * PullRecycler 接口，声明 PullRecycler 应有的功能
 */

public interface IPullRecycler {

  /**
   * 刷新数据成功
   */
  void onRefreshSucceed();

  /**
   * 刷新数据失败
   */
  void onRefreshFailed();

  /**
   * 加载更多数据成功
   *
   * @param hasMore 是否还有更多数据
   */
  void onLoadMoreSucceed(boolean hasMore);

  /**
   * 加载更多数据失败
   *
   * @param error 错误信息
   */
  void onLoadMoreFailed(String error);

  void enableLoadMore(boolean enable);

  void enableRefresh(boolean enable);

  void setAdapter(@NonNull RecyclerView.Adapter adapter);

  void setLayoutManager(@NonNull ILayoutManager layoutManager);

  void setItemAnimator(@NonNull RecyclerView.ItemAnimator itemAnimator);

  void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration);

  void addOnScrollListener(@NonNull RecyclerView.OnScrollListener onScrollListener);

  void setLoadMoreHandler(@NonNull ILoadMoreHandler loadMoreHandler);

  void setLoadMoreUIHandler(@NonNull ILoadMoreUIHandler loadMoreUIHandler);

  void setRefreshHandler(@NonNull IRefreshHandler refreshHandler);

  void useDefaultLoadMore();
}
