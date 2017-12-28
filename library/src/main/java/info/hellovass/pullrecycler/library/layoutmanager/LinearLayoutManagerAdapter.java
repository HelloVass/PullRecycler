package info.hellovass.pullrecycler.library.layoutmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hello on 2017/12/27.
 *
 * LinearLayoutManager 适配器
 */

public final class LinearLayoutManagerAdapter implements ILayoutManager {

  private LinearLayoutManager mLinearLayoutManager;

  private LinearLayoutManagerAdapter() {
    throw new AssertionError();
  }

  public LinearLayoutManagerAdapter(@NonNull LinearLayoutManager linearLayoutManager) {
    mLinearLayoutManager = linearLayoutManager;
  }

  @Override public int findFirstVisibleItemPosition() {

    return mLinearLayoutManager.findFirstVisibleItemPosition();
  }

  @Override public int findFirstCompletelyVisibleItemPosition() {

    return mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
  }

  @Override public int findLastVisibleItemPosition() {

    return mLinearLayoutManager.findLastVisibleItemPosition();
  }

  @Override public int findLastCompletelyVisibleItemPosition() {

    return mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
  }

  @Override public View findViewByPosition(int position) {

    return mLinearLayoutManager.findViewByPosition(position);
  }

  @Override public int getPosition(View view) {

    return mLinearLayoutManager.getPosition(view);
  }

  @Override public int getItemCount() {

    return mLinearLayoutManager.getItemCount();
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {

    return mLinearLayoutManager;
  }
}
