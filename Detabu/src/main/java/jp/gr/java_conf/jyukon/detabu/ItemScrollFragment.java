package jp.gr.java_conf.jyukon.detabu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.model.Item;

public class ItemScrollFragment extends DetabuBaseFragment {
    @Inject ICardScrollView mCardScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mCardScrollView.toView();
    }

    public void setItems(List<Item> items) {
        mCardScrollView.setItems(items);
        mCardScrollView.activate();
    }
}
