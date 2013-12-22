package jp.gr.java_conf.jyukon.detabu;

import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

public interface ICard {
    ICard setFullScreenImages (boolean fullScreenImages);
    ICard setText(String text);
    ICard setInfo(String info);
    ICard addImage(Uri uri);
    void clearImages();
    int getNumberOfImages();
    RemoteViews toRemoteViews();
    View toView ();
}
