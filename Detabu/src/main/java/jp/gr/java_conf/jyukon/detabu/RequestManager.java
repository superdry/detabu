package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;

public class RequestManager {

    private RequestProxy mRequestProxy;

    public RequestManager(Context context) {
        mRequestProxy = new RequestProxy(context);
    }

    public RequestProxy doRequest() {
        return mRequestProxy;
    }
}