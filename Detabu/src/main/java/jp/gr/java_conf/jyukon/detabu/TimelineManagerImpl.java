package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;

import com.google.android.glass.timeline.TimelineManager;


public class TimelineManagerImpl implements ITimelineManager {

    private TimelineManager mTimelineManager;

    public TimelineManagerImpl(Context context) {
        mTimelineManager = TimelineManager.from(context);
    }

    @Override
    public ILiveCard getLiveCard(String id) {
        return new LiveCardImpl(mTimelineManager, id);
    }
}
