package jp.gr.java_conf.jyukon.detabu;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import lombok.Getter;

public class DetabuApplication extends Application {
    @Getter private ObjectGraph applicationGraph;

    @Override public void onCreate() {
        super.onCreate();
        applicationGraph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new RequestManagerModule(this)
        );
    }
}
