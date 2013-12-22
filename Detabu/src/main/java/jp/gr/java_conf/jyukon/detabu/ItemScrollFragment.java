package jp.gr.java_conf.jyukon.detabu;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.model.Item;

public class ItemScrollFragment extends DetabuBaseFragment {

    public static final String KEY_ARGS_ITEMS = "items";
    @Inject ICardScrollView mCardScrollView;

    public static ItemScrollFragment newInstance(List<Item> items) {
        ItemScrollFragment fragment = new ItemScrollFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_ARGS_ITEMS, (ArrayList<? extends Parcelable>) items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mCardScrollView.toView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCardScrollView.setItems(getArguments().<Item>getParcelableArrayList(KEY_ARGS_ITEMS));
        mCardScrollView.activate();
    }
}
