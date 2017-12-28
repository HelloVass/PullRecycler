package info.hellovass.pullrecycler.library.loadmore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import info.hellovass.pullrecycler.library.R;

/**
 * Created by hello on 2017/12/27.
 */

public final class DefaultLoadMore extends FrameLayout implements ILoadMoreUIHandler {

  private TextView mTvLoading;

  public static DefaultLoadMore create(Context context) {
    return new DefaultLoadMore(context);
  }

  public DefaultLoadMore(@NonNull Context context) {
    this(context, null);
  }

  public DefaultLoadMore(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public DefaultLoadMore(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    // 默认不可见
    setVisibility(GONE);

    View.inflate(context, R.layout.view_loadmore_default, this);
    setupViews();
  }

  protected void setupViews() {

    mTvLoading = findViewById(R.id.tv_loading);
  }

  @Override public void onLoading() {

    setVisibility(VISIBLE);
    mTvLoading.setText("加载中...");
  }

  @Override public void onLoadSucceed(boolean hasMore) {

    if (!hasMore) {

      setVisibility(VISIBLE);
      mTvLoading.setText("没有更多数据啦");
    } else {

      setVisibility(GONE);
    }
  }

  @Override public void onLoadFailed(String error) {

    setVisibility(VISIBLE);
    mTvLoading.setText(error);
  }

  @Override public View getConvertView() {

    return this;
  }
}
