package jp.gr.java_conf.jyukon.detabu;

import android.app.PendingIntent;
import android.widget.RemoteViews;

public interface ILiveCard {
    ILiveCard setNonSilent(boolean nonSilent);

    ILiveCard setViews(RemoteViews views);

    ILiveCard setAction(PendingIntent pendingIntent);

    void publish();

    boolean isPublished();

    void unpublish();
}
