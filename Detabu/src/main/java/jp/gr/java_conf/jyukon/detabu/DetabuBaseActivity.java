package jp.gr.java_conf.jyukon.detabu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import dagger.ObjectGraph;

public abstract class DetabuBaseActivity  extends FragmentActivity {
    private ObjectGraph activityGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetabuApplication application = (DetabuApplication) getApplication();
        activityGraph = application.getApplicationGraph().plus(DetabuModule.getActivityModule(this));
        activityGraph.inject(this);
    }

    @Override protected void onDestroy() {
        activityGraph = null;

        super.onDestroy();
    }

    public void inject(Object object) {
        activityGraph.inject(object);
    }
}
