package jp.gr.java_conf.jyukon.detabu.stub;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.DetabuBaseActivity;
import jp.gr.java_conf.jyukon.detabu.ICardScrollView;
import jp.gr.java_conf.jyukon.detabu.event.ItemChangedEvent;
import jp.gr.java_conf.jyukon.detabu.model.Item;

public class CardScrollViewImpl implements ICardScrollView, ViewPager.OnPageChangeListener {
    private final ViewPager mViewPager;
    @Inject ItemCardPagerAdapter mAdapter;
    @Inject Bus bus;

    public CardScrollViewImpl(Context context) {
        mViewPager = new ViewPager(context);
        mViewPager.setOnPageChangeListener(this);
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
        bus.post(new ItemChangedEvent(items.get(0)));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        bus.post(new ItemChangedEvent(mAdapter.items.get(position)));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}