package info.hellovass.pullrecycler.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import info.hellovass.pullrecycler.library.layoutmanager.ILayoutManager;
import info.hellovass.pullrecycler.library.loadmore.DefaultLoadMore;
import info.hellovass.pullrecycler.library.loadmore.ILoadMoreHandler;
import info.hellovass.pullrecycler.library.loadmore.ILoadMoreUIHandler;
import info.hellovass.pullrecycler.library.refresh.IRefreshHandler;

/**
 * Created by hello on 2017/12/27.
 */

public final class PullRecycler extends FrameLayout implements IPullRecycler {

  private SwipeRefreshLayout mSwipeRefreshLayout;

  private RecyclerView mRecyclerView;

  private ViewGroup mLoadMoreContainer;

  private ILayoutManager mILayoutManager;

  private RecyclerView.Adapter mAdapter;

  private boolean mLoadMoreEnabled = false;

  private boolean mRefreshEnabled = false;

  private boolean mLoading = false;

  private boolean mLoadFailed = false;

  private boolean mHasMore = true;

  private ILoadMoreHandler mILoadMoreHandler;

  private ILoadMoreUIHandler mILoadMoreUIHandler;

  private IRefreshHandler mIRefreshHandler;

  private final SwipeRefreshLayout.OnRefreshListener mOnRefreshListener =
      new SwipeRefreshLayout.OnRefreshListener() {

        @Override public void onRefresh() {

          if (!mRefreshEnabled) {
            return;
          }

          if (mLoading) {
            return;
          }

          // 将加载状态设置为 true
          mLoading = true;

          // 刷新
          mIRefreshHandler.onRefresh();
        }
      };

  private final SwipeRefreshLayout.OnChildScrollUpCallback mOnChildScrollUpCallback =
      new SwipeRefreshLayout.OnChildScrollUpCallback() {

        @Override public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {

          return (mRecyclerView != null)
              && (mILayoutManager.findFirstCompletelyVisibleItemPosition() != 0);
        }
      };

  private final RecyclerView.OnScrollListener mOnReachBottomListener =
      new RecyclerView.OnScrollListener() {

        @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

          if (mLoadMoreEnabled
              && mILayoutManager.findLastCompletelyVisibleItemPosition()
              >= mILayoutManager.getItemCount() - 1) {

            onReachBottom();
          }
        }
      };

  public PullRecycler(@NonNull Context context) {
    this(context, null);
  }

  public PullRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PullRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    View.inflate(context, R.layout.layout_pull_recycler, this);
    setupViews();
    setupCallbacks();
  }

  protected void setupViews() {

    mSwipeRefreshLayout = findViewById(R.id.sw_refresh_layout);
    mRecyclerView = findViewById(R.id.rcv_list);
    mLoadMoreContainer = findViewById(R.id.fl_loadmore_container);
  }

  protected void setupCallbacks() {

    // 刷新监听器
    mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

    // 检测 RecyclerView 是否已经到顶了
    mSwipeRefreshLayout.setOnChildScrollUpCallback(mOnChildScrollUpCallback);

    // 加载更多监听器
    mRecyclerView.addOnScrollListener(mOnReachBottomListener);
  }

  protected void onReachBottom() {

    if (mLoadFailed) {
      return;
    }

    if (mLoading) {
      return;
    }

    if (!mHasMore) {
      return;
    }

    // 将加载状态设置为 true
    mLoading = true;

    // 禁用下拉刷新功能
    mSwipeRefreshLayout.setEnabled(false);

    // 显示正在加载中
    mILoadMoreUIHandler.onLoading();

    // 加载更多
    mILoadMoreHandler.onLoadMore();
  }

  @Override public void onRefreshSucceed() {

    mLoadFailed = false;
    mLoading = false;

    // 通知下拉刷新组件停止菊花动画
    mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override public void onRefreshFailed() {

    mLoadFailed = true;
    mLoading = false;

    // 通知下拉刷新组件停止菊花动画
    mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override public void onLoadMoreSucceed(boolean hasMore) {

    mLoadFailed = false;
    mLoading = false;
    mHasMore = hasMore;

    // 启用下拉刷新功能
    mSwipeRefreshLayout.setEnabled(true);

    // 通知 LoadMore 组件加载更多数据成功
    mILoadMoreUIHandler.onLoadSucceed(hasMore);
  }

  @Override public void onLoadMoreFailed(String error) {

    mLoadFailed = true;
    mLoading = false;

    // 启用下拉刷新功能
    mSwipeRefreshLayout.setEnabled(true);

    // 通知 LoadMore 组件加载更多数据失败
    mILoadMoreUIHandler.onLoadFailed(error);
  }

  @Override public void enableLoadMore(boolean enable) {

    mLoadMoreEnabled = enable;
  }

  @Override public void enableRefresh(boolean enable) {

    mRefreshEnabled = enable;
    mSwipeRefreshLayout.setEnabled(enable);
  }

  @Override public void setAdapter(@NonNull RecyclerView.Adapter adapter) {

    mAdapter = adapter;
    mRecyclerView.setAdapter(adapter);
  }

  @Override public void setLayoutManager(@NonNull ILayoutManager layoutManager) {

    mILayoutManager = layoutManager;
    mRecyclerView.setLayoutManager(layoutManager.getLayoutManager());
  }

  @Override public void setItemAnimator(@NonNull RecyclerView.ItemAnimator itemAnimator) {

    mRecyclerView.setItemAnimator(itemAnimator);
  }

  @Override public void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {

    mRecyclerView.addItemDecoration(itemDecoration);
  }

  @Override
  public void addOnScrollListener(@NonNull RecyclerView.OnScrollListener onScrollListener) {

    mRecyclerView.addOnScrollListener(onScrollListener);
  }

  @Override public void setLoadMoreHandler(@NonNull ILoadMoreHandler loadMoreHandler) {

    mILoadMoreHandler = loadMoreHandler;
  }

  @Override public void setLoadMoreUIHandler(@NonNull ILoadMoreUIHandler loadMoreUIHandler) {

    mILoadMoreUIHandler = loadMoreUIHandler;

    if (mLoadMoreContainer.getChildCount() > 0) { // 如果已经有子 View，clear
      mLoadMoreContainer.removeAllViews();
    }

    // 将 LoadMore 组件添加到 mLoadMoreContainer 中
    mLoadMoreContainer.addView(loadMoreUIHandler.getConvertView());
  }

  @Override public void setRefreshHandler(@NonNull IRefreshHandler refreshHandler) {

    mIRefreshHandler = refreshHandler;
  }

  @Override public void useDefaultLoadMore() {

    if (mLoadMoreContainer.getChildCount() > 0) {

      mLoadMoreContainer.removeAllViews();
    }

    mILoadMoreUIHandler = DefaultLoadMore.create(getContext());
    mLoadMoreContainer.addView(mILoadMoreUIHandler.getConvertView(),
        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
  }
}
