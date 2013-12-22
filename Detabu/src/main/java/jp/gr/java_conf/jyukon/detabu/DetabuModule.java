package jp.gr.java_conf.jyukon.detabu;

import android.app.Activity;
import android.app.Service;
import android.os.Build;

import jp.gr.java_conf.jyukon.detabu.stub.ActivityStubModule;
import jp.gr.java_conf.jyukon.detabu.stub.ServiceStubModule;

public class DetabuModule {
    static boolean isGlass = "Glass 1".equals(Build.MODEL);

    public static Object getActivityModule(Activity activity) {
        return isGlass ? new ActivityModule(activity) : new ActivityStubModule(activity);
    }

    public static Object getServiceModule(Service service) {
        return isGlass ? new ServiceModule(service) : new ServiceStubModule(service);
    }
}