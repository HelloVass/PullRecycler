package info.hellovass.pullrecyclersample.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import info.hellovass.pullrecycler.library.PullRecycler;
import info.hellovass.pullrecycler.library.layoutmanager.LinearLayoutManagerAdapter;
import info.hellovass.pullrecycler.library.loadmore.ILoadMoreHandler;
import info.hellovass.pullrecycler.library.refresh.IRefreshHandler;
import info.hellovass.pullrecyclersample.R;
import info.hellovass.pullrecyclersample.contract.SampleContract;
import info.hellovass.pullrecyclersample.presenter.SamplePresenter;
import info.hellovass.pullrecyclersample.view.adapter.SampleAdapter;

/**
 * Created by hello on 2017/12/27.
 */

public class SampleActivity extends AppCompatActivity implements SampleContract.View {

  private PullRecycler mPullRecycler;

  private SampleAdapter mSampleAdapter;

  private SamplePresenter mSamplePresenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initPresenter();

    initWidgets();

    setupCallbacks();

    initData();
  }

  protected void initPresenter() {

    mSamplePresenter = new SamplePresenter(this);
  }

  private void initWidgets() {

    // 加载布局
    setContentView(R.layout.activity_sample);

    mPullRecycler = findViewById(R.id.pullrecycler);
  }

  private void setupCallbacks() {

    mPullRecycler.setItemAnimator(new DefaultItemAnimator());
    mPullRecycler.setLayoutManager(new LinearLayoutManagerAdapter(new LinearLayoutManager(this)));

    mPullRecycler.enableRefresh(true);
    mPullRecycler.setRefreshHandler(new IRefreshHandler() {

      @Override public void onRefresh() {

        mSamplePresenter.refreshPage();
      }
    });

    mPullRecycler.enableLoadMore(true);
    mPullRecycler.setLoadMoreHandler(new ILoadMoreHandler() {

      @Override public void onLoadMore() {

        mSamplePresenter.loadNextPage();
      }
    });

    // 使用默认的加载组件
    mPullRecycler.useDefaultLoadMore();

    // 初始化 Adapter
    mSampleAdapter = new SampleAdapter(mSamplePresenter.getDataSource());

    mPullRecycler.setAdapter(mSampleAdapter);
  }

  protected void initData() {

    mSamplePresenter.initPage();
  }

  @Override public void onInitPageSucceed(boolean hasMore) {

    mSampleAdapter.notifyDataSetChanged();
    mPullRecycler.onLoadMoreSucceed(hasMore);
  }

  @Override public void onInitPageFailed(String error) {

    mPullRecycler.onLoadMoreFailed(error);
  }

  @Override public void onRefreshPageSucceed(boolean hasMore) {

    mSampleAdapter.notifyDataSetChanged();
    mPullRecycler.onRefreshSucceed();
  }

  @Override public void onRefreshPageFailed(String error) {

    mPullRecycler.onRefreshFailed();
  }

  @Override public void onLoadNextPageSucceed(int positionStart, int size, boolean hasMore) {

    mSampleAdapter.notifyItemRangeInserted(positionStart, size);
    mPullRecycler.onLoadMoreSucceed(hasMore);
  }

  @Override public void onLoadNextPageFailed(String error) {

    mPullRecycler.onLoadMoreFailed(error);
  }
}
