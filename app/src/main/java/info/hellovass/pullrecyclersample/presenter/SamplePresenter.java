package info.hellovass.pullrecyclersample.presenter;

import info.hellovass.pullrecyclersample.contract.SampleContract;
import info.hellovass.pullrecyclersample.repo.SampleRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/12/28.
 */

public class SamplePresenter implements SampleContract.Presenter {

  private SampleContract.View mView;

  private SampleRepository mRepository;

  private int mPageNum = 1;

  private List<String> mDatas = new ArrayList<>();

  public SamplePresenter(SampleContract.View view) {

    mView = view;
    mRepository = new SampleRepository();
  }

  @Override public List<String> getDataSource() {

    return mDatas;
  }

  @Override public void initPage() {

    mRepository.loadData(mPageNum, new SampleRepository.OnDataLoadedListener() {

      @Override public void onDataLoaded(List<String> datas) {

        mDatas.addAll(datas);
        mView.onInitPageSucceed(hasMore(datas));
        mPageNum++;
      }
    });
  }

  @Override public void refreshPage() {

    mPageNum = 1;

    mRepository.loadData(mPageNum, new SampleRepository.OnDataLoadedListener() {

      @Override public void onDataLoaded(List<String> datas) {

        mDatas.clear();
        mDatas.addAll(datas);
        mView.onRefreshPageSucceed(hasMore(datas));
        mPageNum++;
      }
    });
  }

  @Override public void loadNextPage() {

    mRepository.loadData(mPageNum, new SampleRepository.OnDataLoadedListener() {

      @Override public void onDataLoaded(List<String> datas) {

        mDatas.addAll(datas);
        mView.onLoadNextPageSucceed(mDatas.size() - datas.size(), datas.size(), hasMore(datas));
        mPageNum++;
      }
    });
  }

  private boolean hasMore(List<String> result) {

    return (result != null && result.size() >= 10);
  }
}
