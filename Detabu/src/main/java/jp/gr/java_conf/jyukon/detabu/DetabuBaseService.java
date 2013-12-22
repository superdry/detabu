package jp.gr.java_conf.jyukon.detabu;

import android.app.Service;

import dagger.ObjectGraph;

public abstract class DetabuBaseService extends Service {
    private ObjectGraph serviceGraph;

    @Override
    public void onCreate() {
        DetabuApplication application = (DetabuApplication) getApplication();
        serviceGraph = application.getApplicationGraph().plus(DetabuModule.getServiceModule(this));
        serviceGraph.inject(this);
    }

    @Override
    public void onDestroy() {
        serviceGraph = null;

        super.onDestroy();
    }

    public void inject(Object object) {
        serviceGraph.inject(object);
    }
}
