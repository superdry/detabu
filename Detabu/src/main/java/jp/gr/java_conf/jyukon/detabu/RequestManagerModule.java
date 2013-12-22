package jp.gr.java_conf.jyukon.detabu;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class RequestManagerModule {
    private Context mApplicationContext;

    public RequestManagerModule(DetabuApplication application) {
        this.mApplicationContext = application.getApplicationContext();
    }

    @Provides
    @Singleton
    RequestManager provideRequestManager() {
        return new RequestManager(mApplicationContext);
    }
}
