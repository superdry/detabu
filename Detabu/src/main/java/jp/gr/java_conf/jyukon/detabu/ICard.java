package jp.gr.java_conf.jyukon.detabu;

import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import com.google.android.glass.app.Card;

public interface ICard {
    ICard setImageLayout(Card.ImageLayout imageLayout);
    ICard setText(String text);
    ICard setFootnote(String info);
    ICard addImage(Uri uri);
    void clearImages();
    int getImageCount();
    RemoteViews toRemoteViews();
    View toView ();
}
