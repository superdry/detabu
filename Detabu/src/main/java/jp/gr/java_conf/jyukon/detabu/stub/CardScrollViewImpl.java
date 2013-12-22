package jp.gr.java_conf.jyukon.detabu.stub;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.DetabuBaseActivity;
import jp.gr.java_conf.jyukon.detabu.ICardScrollView;
import jp.gr.java_conf.jyukon.detabu.model.Item;

public class CardScrollViewImpl implements ICardScrollView {
    private final ViewPager mViewPager;
    @Inject ItemCardPagerAdapter mAdapter;

    public CardScrollViewImpl(Context context) {
        mViewPager = new ViewPager(context);
        ((DetabuBaseActivity)context).inject(this);
    }

    @Override
    public void activate() {
    }

    @Override
    public View toView() {
        return mViewPager;
    }

    @Override
    public void setItems(List<Item> items) {
        mAdapter.setItems(items);
        mViewPager.setAdapter(mAdapter);
    }
}