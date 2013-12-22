package jp.gr.java_conf.jyukon.detabu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import dagger.ObjectGraph;

public abstract class DetabuBaseActivity  extends FragmentActivity {
    @Inject Bus bus;

    private ObjectGraph activityGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetabuApplication application = (DetabuApplication) getApplication();
        activityGraph = application.getApplicationGraph().plus(DetabuModule.getActivityModule(this));
        activityGraph.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override protected void onDestroy() {
        activityGraph = null;

        super.onDestroy();
    }

    public void inject(Object object) {
        activityGraph.inject(object);
    }
}
