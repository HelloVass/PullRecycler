package info.hellovass.pullrecycler.library.layoutmanager;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hello on 2017/12/27.
 */

public final class GridLayoutManagerAdapter implements ILayoutManager{

  private GridLayoutManager mGridLayoutManager;

  private GridLayoutManagerAdapter() {
    throw new AssertionError();
  }

  public GridLayoutManagerAdapter(GridLayoutManager gridLayoutManager) {
    mGridLayoutManager = gridLayoutManager;
  }

  @Override public int findFirstVisibleItemPosition() {

    return mGridLayoutManager.findFirstVisibleItemPosition();
  }

  @Override public int findFirstCompletelyVisibleItemPosition() {

    return mGridLayoutManager.findFirstCompletelyVisibleItemPosition();
  }

  @Override public int findLastVisibleItemPosition() {

    return mGridLayoutManager.findLastVisibleItemPosition();
  }

  @Override public int findLastCompletelyVisibleItemPosition() {

    return mGridLayoutManager.findLastCompletelyVisibleItemPosition();
  }

  @Override public int getPosition(View view) {

    return mGridLayoutManager.getPosition(view);
  }

  @Override public int getItemCount() {

    return mGridLayoutManager.getItemCount();
  }

  @Override public View findViewByPosition(int position) {

    return mGridLayoutManager.findViewByPosition(position);
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {

    return mGridLayoutManager;
  }
}
