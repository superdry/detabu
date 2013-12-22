package jp.gr.java_conf.jyukon.detabu;

import android.app.PendingIntent;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.TimelineManager;


public class LiveCardImpl implements ILiveCard {

    private LiveCard mLiveCard;

    public LiveCardImpl(TimelineManager timelineManager, String id) {
        mLiveCard = timelineManager.getLiveCard(id);
    }

    @Override
    public ILiveCard setNonSilent(boolean nonSilent) {
        mLiveCard.setNonSilent(nonSilent);
        return this;
    }

    @Override
    public ILiveCard setViews(RemoteViews views) {
        mLiveCard.setViews(views);
        return this;
    }

    @Override
    public ILiveCard setAction(PendingIntent pendingIntent) {
        mLiveCard.setAction(pendingIntent);
        return this;
    }

    @Override
    public void publish() {
        mLiveCard.publish();
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
