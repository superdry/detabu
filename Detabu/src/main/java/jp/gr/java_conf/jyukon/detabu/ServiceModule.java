package jp.gr.java_conf.jyukon.detabu;

import android.app.Service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                DetabuService.class,
                LiveCardImpl.class,
                LiveCardRenderer.class
        },
        complete = false,
        library = true
)
public class ServiceModule {

    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @Singleton
    ITimelineManager provideITimelineManager() {
        return new TimelineManagerImpl(mService);
    }

    @Provides
    ICard provideICard() {
        return new CardImpl(mService);
    }

    @Provides
    LiveCardRenderer provideLiveCardRenderer() {
        return new LiveCardRendererImpl(mService);
    }
}
