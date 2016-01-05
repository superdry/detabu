package jp.gr.java_conf.jyukon.detabu;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.RemoteViews;


public interface ICard {
    ICard setText(String text);
    ICard setFootnote(String info);
    ICard addImage(Bitmap bitmap);
    void clearImages();
    RemoteViews getRemoteViews();
    View getView ();
}
