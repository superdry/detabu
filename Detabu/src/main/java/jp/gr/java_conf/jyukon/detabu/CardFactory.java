package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;

import com.google.android.glass.widget.CardBuilder;

public class CardFactory implements ICardFactory {
    Context mContext;

    public CardFactory(Context context) {
        mContext = context;
    }

    @Override
    public ICard getCard() {
        return new CardImpl(mContext, CardBuilder.Layout.CAPTION);
    }
}