package jp.gr.java_conf.jyukon.detabu.stub;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.app.NotificationCompat;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;

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
    public ILiveCard setDirectRenderingEnabled(boolean enable) {
        return this;
    }

    @Override
    public SurfaceHolder getSurfaceHolder() {
        return new DummySurfaceHolder();
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

    static class DummySurfaceHolder implements SurfaceHolder {

        @Override
        public void addCallback(Callback callback) {}

        @Override
        public void removeCallback(Callback callback) {}

        @Override
        public boolean isCreating() {
            return false;
        }

        @Override
        public void setType(int i) {}

        @Override
        public void setFixedSize(int i, int i2) {}

        @Override
        public void setSizeFromLayout() {}

        @Override
        public void setFormat(int i) {}

        @Override
        public void setKeepScreenOn(boolean b) {}

        @Override
        public Canvas lockCanvas() {
            return null;
        }

        @Override
        public Canvas lockCanvas(Rect rect) {
            return null;
        }

        @Override
        public void unlockCanvasAndPost(Canvas canvas) {}

        @Override
        public Rect getSurfaceFrame() {
            return null;
        }

        @Override
        public Surface getSurface() {
            return null;
        }
    }
}