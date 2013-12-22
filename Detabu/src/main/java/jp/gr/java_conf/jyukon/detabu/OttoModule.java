package jp.gr.java_conf.jyukon.detabu;


import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class OttoModule {
    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }
}
