package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.glass.widget.CardScrollView;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.event.ItemChangedEvent;
import jp.gr.java_conf.jyukon.detabu.event.ItemSelectedEvent;
import jp.gr.java_conf.jyukon.detabu.model.Item;

public class CardScrollViewImpl implements ICardScrollView, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private final CardScrollView mCardScrollView;
    @Inject ItemCardScrollAdapter mAdapter;
    @Inject Bus bus;

    public CardScrollViewImpl(Context context) {
        mCardScrollView = new CardScrollView(context);
        mCardScrollView.setOnItemSelectedListener(this);
        mCardScrollView.setOnItemClickListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bus.post(new ItemChangedEvent(mAdapter.items.get(position)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        bus.post(new ItemSelectedEvent(mAdapter.items.get(position)));
    }
}
