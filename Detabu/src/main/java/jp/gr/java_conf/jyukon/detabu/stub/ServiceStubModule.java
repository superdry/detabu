package jp.gr.java_conf.jyukon.detabu.stub;

import android.app.Service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.gr.java_conf.jyukon.detabu.DetabuService;
import jp.gr.java_conf.jyukon.detabu.ICard;
import jp.gr.java_conf.jyukon.detabu.ITimelineManager;
import jp.gr.java_conf.jyukon.detabu.LiveCardRenderer;

@Module(
    injects = {
        DetabuService.class,
        LiveCardImpl.class,
        LiveCardRenderer.class,
        LiveCardRendererImpl.class
    },
    complete = false,
    library = true
)
public class ServiceStubModule {

    private Service mService;

    public ServiceStubModule(Service service) {
        mService = service;
    }

    @Provides
    @Singleton
    ITimelineManager provideITimelineManager() {
        return new TimelineManagerImpl(mService);
    }

    @Provides
    ICard provideICard() {
        return new NotificationCard(mService);
    }

    @Provides
    LiveCardRenderer provideLiveCardRenderer() {
        return new LiveCardRendererImpl(mService);
    }
}