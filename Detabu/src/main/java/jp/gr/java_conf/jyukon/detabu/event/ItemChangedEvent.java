package jp.gr.java_conf.jyukon.detabu.event;

import jp.gr.java_conf.jyukon.detabu.model.Item;
import lombok.Getter;

public class ItemChangedEvent {
    @Getter final Item item;

    public ItemChangedEvent(Item item) {
        this.item = item;
    }
}
