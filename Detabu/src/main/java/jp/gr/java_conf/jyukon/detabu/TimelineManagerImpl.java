package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;

public class TimelineManagerImpl implements ITimelineManager {

    private Context mContext;
    public TimelineManagerImpl(Context context) {
        mContext = context;
    }

    @Override
    public ILiveCard createLiveCard(String tag) {
        return new LiveCardImpl(mContext, tag);
    }
}
