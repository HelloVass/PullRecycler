package info.hellovass.pullrecycler.library.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by hello on 2017/12/27.
 */

public final class StaggeredGridLayoutManagerAdapter implements ILayoutManager {

  private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

  private StaggeredGridLayoutManagerAdapter() {
    throw new AssertionError();
  }

  public StaggeredGridLayoutManagerAdapter(StaggeredGridLayoutManager staggeredGridLayoutManager) {
    mStaggeredGridLayoutManager = staggeredGridLayoutManager;
  }

  @Override public int findFirstVisibleItemPosition() {

    return 0;
  }

  @Override public int findFirstCompletelyVisibleItemPosition() {

    return 0;
  }

  @Override public int findLastVisibleItemPosition() {

    return 0;
  }

  @Override public int findLastCompletelyVisibleItemPosition() {

    return 0;
  }

  @Override public int getPosition(View view) {

    return mStaggeredGridLayoutManager.getPosition(view);
  }

  @Override public int getItemCount() {

    return mStaggeredGridLayoutManager.getItemCount();
  }

  @Override public View findViewByPosition(int position) {

    return mStaggeredGridLayoutManager.findViewByPosition(position);
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {

    return mStaggeredGridLayoutManager;
  }
}
