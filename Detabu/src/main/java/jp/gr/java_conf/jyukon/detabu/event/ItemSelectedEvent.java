package jp.gr.java_conf.jyukon.detabu.event;

import jp.gr.java_conf.jyukon.detabu.model.Item;
import lombok.Getter;

public class ItemSelectedEvent {
    @Getter
    final Item item;

    public ItemSelectedEvent(Item item) {
        this.item = item;
    }
}
