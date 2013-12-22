package jp.gr.java_conf.jyukon.detabu;

import android.app.NotificationManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.view.LayoutInflater;

import com.google.android.glass.sample.compass.OrientationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class AndroidModule {
    private final DetabuApplication mApplication;

    public AndroidModule(DetabuApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) mApplication.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    SensorManager provideSensorManager() {
        return (SensorManager) mApplication.getSystemService(Context.SENSOR_SERVICE);
    }

    @Provides
    @Singleton
    OrientationManager provideOrientationManager(SensorManager sensorManager, LocationManager locationManager) {
        return new OrientationManager(sensorManager, locationManager);
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager() {
        return (NotificationManager) mApplication.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
