package info.hellovass.pullrecyclersample.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import info.hellovass.pullrecyclersample.R;
import java.util.List;

/**
 * Created by hello on 2017/12/27.
 */

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

  private List<String> mDatas;

  public SampleAdapter(List<String> datas) {
    mDatas = datas;
  }

  @Override public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    return new SampleViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_sample, parent, false));
  }

  @Override public void onBindViewHolder(SampleViewHolder holder, int position) {

    holder.bind(mDatas.get(position));
  }

  @Override public int getItemCount() {

    return mDatas.size();
  }

  static final class SampleViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvTitle;

    public SampleViewHolder(View itemView) {
      super(itemView);
      mTvTitle = itemView.findViewById(R.id.tv_title);
    }

    public void bind(String title) {
      mTvTitle.setText(title);
    }
  }
}
