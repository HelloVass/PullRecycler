package info.hellovass.pullrecycler.library.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hello on 2017/12/27.
 *
 * ILayoutManager，我们期望的接口。
 * 待适配类的为 LinearLayoutManager、GridLayoutManager、StaggeredGridLayout，
 * 所以决定使用适配器模式来适配
 */

public interface ILayoutManager {

  int findFirstVisibleItemPosition();

  int findFirstCompletelyVisibleItemPosition();

  int findLastVisibleItemPosition();

  int findLastCompletelyVisibleItemPosition();

  int getPosition(View view);

  int getItemCount();

  View findViewByPosition(int position);

  RecyclerView.LayoutManager getLayoutManager();
}
