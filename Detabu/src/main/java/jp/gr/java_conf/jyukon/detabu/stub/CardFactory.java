package jp.gr.java_conf.jyukon.detabu.stub;

import android.content.Context;

import jp.gr.java_conf.jyukon.detabu.ICard;
import jp.gr.java_conf.jyukon.detabu.ICardFactory;

public class CardFactory implements ICardFactory {
    Context mContext;

    public CardFactory(Context context) {
        mContext = context;
    }

    @Override
    public ICard getCard() {
        return new FullScreenImageCard(mContext);
    }
}