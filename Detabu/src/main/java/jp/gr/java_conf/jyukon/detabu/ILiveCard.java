package jp.gr.java_conf.jyukon.detabu;

import android.app.PendingIntent;
import android.view.SurfaceHolder;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;

public interface ILiveCard {
    ILiveCard setDirectRenderingEnabled(boolean enable);

    SurfaceHolder getSurfaceHolder();

    ILiveCard setAction(PendingIntent pendingIntent);

    void publish();

    boolean isPublished();

    void unpublish();
}
