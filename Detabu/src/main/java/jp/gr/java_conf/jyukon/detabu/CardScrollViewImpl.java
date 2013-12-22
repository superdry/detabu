package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;
import android.view.View;

import com.google.android.glass.widget.CardScrollView;

import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.model.Item;

public class CardScrollViewImpl implements ICardScrollView {
    private final CardScrollView mCardScrollView;
    @Inject ItemCardScrollAdapter mAdapter;

    public CardScrollViewImpl(Context context) {
        mCardScrollView = new CardScrollView(context);
    }

    @Override
    public void activate() {
        mCardScrollView.activate();
    }

    @Override
    public View toView() {
        return mCardScrollView;
    }

    @Override
    public void setItems(List<Item> items) {
        mAdapter.setItems(items);
        mCardScrollView.setAdapter(mAdapter);
    }
}
