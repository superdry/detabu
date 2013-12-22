package jp.gr.java_conf.jyukon.detabu.stub;

import android.content.Context;

import jp.gr.java_conf.jyukon.detabu.DetabuBaseService;
import jp.gr.java_conf.jyukon.detabu.ILiveCard;
import jp.gr.java_conf.jyukon.detabu.ITimelineManager;


public class TimelineManagerImpl implements ITimelineManager {

    private Context mContext;

    public TimelineManagerImpl(Context context) {
        mContext = context;
    }

    @Override
    public ILiveCard getLiveCard(String id) {
        ILiveCard liveCard = new LiveCardImpl(mContext, id);
        ((DetabuBaseService)mContext).inject(liveCard);
        return liveCard;
    }
}
