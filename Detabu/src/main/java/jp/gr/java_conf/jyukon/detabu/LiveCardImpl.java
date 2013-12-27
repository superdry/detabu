package jp.gr.java_conf.jyukon.detabu;

import android.app.PendingIntent;
import android.view.SurfaceHolder;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.TimelineManager;


public class LiveCardImpl implements ILiveCard {

    private LiveCard mLiveCard;

    public LiveCardImpl(TimelineManager timelineManager, String id) {
        mLiveCard = timelineManager.createLiveCard(id);
    }

    @Override
    public ILiveCard setDirectRenderingEnabled(boolean enable) {
        return this;
    }

    @Override
    public SurfaceHolder getSurfaceHolder() {
        return mLiveCard.getSurfaceHolder();
    }

    @Override
    public ILiveCard setAction(PendingIntent pendingIntent) {
        mLiveCard.setAction(pendingIntent);
        return this;
    }

    @Override
    public void publish() {
        mLiveCard.publish(LiveCard.PublishMode.REVEAL);
    }

    @Override
    public boolean isPublished() {
        return mLiveCard.isPublished();
    }

    @Override
    public void unpublish() {
        mLiveCard.unpublish();
    }
}
