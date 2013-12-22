package jp.gr.java_conf.jyukon.detabu.stub;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.ILiveCard;
import jp.gr.java_conf.jyukon.detabu.R;


public class LiveCardImpl implements ILiveCard {
    @Inject NotificationManager mNotificationManager;
    private RemoteViews mRemoteViews;
    private Context mContext;
    private PendingIntent mPendingIntent;
    private boolean published;

    public LiveCardImpl(Context context, String id) {
        mContext = context;
    }

    @Override
    public ILiveCard setNonSilent(boolean nonSilent) {
        return this;
    }

    @Override
    public ILiveCard setViews(RemoteViews views) {
        mRemoteViews = views;
        if (published)
            publish(); // call publish again to update remote views
        return this;
    }

    @Override
    public ILiveCard setAction(PendingIntent pendingIntent) {
        mPendingIntent = pendingIntent;
        return this;
    }

    @Override
    public void publish() {
        Notification notification = new NotificationCompat.Builder(mContext).setContent(mRemoteViews).setContentIntent(mPendingIntent).setSmallIcon(R.drawable.icon).build();
        mNotificationManager.notify(0, notification);
        published = true;
    }

    @Override
    public boolean isPublished() {
        return published;
    }

    @Override
    public void unpublish() {
        published = false;
    }
}