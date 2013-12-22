package jp.gr.java_conf.jyukon.detabu;

import android.view.View;

import java.util.List;

import jp.gr.java_conf.jyukon.detabu.model.Item;

public interface ICardScrollView {
    void activate();
    View toView();
    void setItems(List<Item> items);
}
