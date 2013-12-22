package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import com.google.android.glass.app.Card;


public class CardImpl implements ICard {

    private Card mCard;

    public CardImpl(Context context) {
        mCard = new Card(context);
    }

    @Override
    public ICard setFullScreenImages(boolean fullScreenImages) {
        mCard.setFullScreenImages(fullScreenImages);
        return this;
    }

    @Override
    public ICard setText(String text) {
        mCard.setText(text);
        return this;
    }

    @Override
    public ICard setInfo(String info) {
        mCard.setInfo(info);
        return this;
    }

    @Override
    public ICard addImage(Uri uri) {
        mCard.addImage(uri);
        return this;
    }

    @Override
    public void clearImages() {
        mCard.clearImages();
    }

    @Override
    public int getNumberOfImages() {
        return mCard.getNumberOfImages();
    }

    @Override
    public RemoteViews toRemoteViews() {
        return mCard.toRemoteViews();
    }

    @Override
    public View toView() {
        return mCard.toView();
    }
}
