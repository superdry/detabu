package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.RemoteViews;

import com.google.android.glass.widget.CardBuilder;


public class CardImpl implements ICard {

    private CardBuilder mCard;

    public CardImpl(Context context, CardBuilder.Layout layout) {
        mCard = new CardBuilder(context, layout);
    }

    @Override
    public ICard setText(String text) {
        mCard.setText(text);
        return this;
    }

    @Override
    public ICard setFootnote(String info) {
        mCard.setFootnote(info);
        return this;
    }

    @Override
    public ICard addImage(Bitmap bitmap) {
        mCard.addImage(bitmap);
        return this;
    }

    @Override
    public void clearImages() {
        mCard.clearImages();
    }

    @Override
    public View getView() {
        return mCard.getView();
    }

    @Override
    public RemoteViews getRemoteViews() {
        return mCard.getRemoteViews();
    }
}
