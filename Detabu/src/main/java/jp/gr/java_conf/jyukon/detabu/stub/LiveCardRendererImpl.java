package jp.gr.java_conf.jyukon.detabu.stub;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.DetabuBaseService;
import jp.gr.java_conf.jyukon.detabu.LiveCardRenderer;
import jp.gr.java_conf.jyukon.detabu.R;

public class LiveCardRendererImpl extends LiveCardRenderer {
    @Inject NotificationManager mNotificationManager;

    public LiveCardRendererImpl(Context context) {
        super(context);
        ((DetabuBaseService)context).inject(this);
    }

    @Override
    public void redrawCard() {
        Notification notification = new NotificationCompat.Builder(mContext).setContent(mCard.getRemoteViews()).setContentIntent(pendingIntent).setSmallIcon(R.drawable.icon).build();
        mNotificationManager.notify(0, notification);
    }
}
