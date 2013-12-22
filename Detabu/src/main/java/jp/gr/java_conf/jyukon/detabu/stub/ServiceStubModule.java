package jp.gr.java_conf.jyukon.detabu.stub;

import android.app.Service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.gr.java_conf.jyukon.detabu.DetabuService;
import jp.gr.java_conf.jyukon.detabu.ICard;
import jp.gr.java_conf.jyukon.detabu.ITimelineManager;

@Module(
    injects = {
        DetabuService.class,
        LiveCardImpl.class
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
}