package jp.gr.java_conf.jyukon.detabu;

import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;

import com.android.volley.Response;
import com.google.android.glass.sample.compass.OrientationManager;
import com.google.android.glass.timeline.LiveCard;

import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.model.Item;
import lombok.Getter;

public class DetabuService extends DetabuBaseService {

    private static final String LIVE_CARD_TAG = "detabu";
    private static final int RADIUS = 500;

    public class DetabuBinder extends Binder {
        DetabuService getService() {
            return DetabuService.this;
        }
    }

    private final DetabuBinder mBinder = new DetabuBinder();
    @Inject OrientationManager mOrientationManager;
    @Inject ITimelineManager mTimelineManager;
    @Inject RequestManager mRequestManager;
    @Inject LiveCardRenderer mRenderer;
    @Getter private List<Item> items;
    private ILiveCard mLiveCard;

    private final OrientationManager.OnChangedListener mListener =
            new OrientationManager.OnChangedListener() {
                @Override
                public void onOrientationChanged(OrientationManager orientationManager) {
                }

                @Override
                public void onLocationChanged(OrientationManager orientationManager) {
                    Location location = orientationManager.getLocation();
                    orientationManager.stop();
                    searchNearbyItems(location);
                }

                @Override
                public void onAccuracyChanged(OrientationManager orientationManager) {
                }
            };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mOrientationManager.addOnChangedListener(mListener);
        mOrientationManager.start();

        if (mOrientationManager.hasLocation()) {
            Location location = mOrientationManager.getLocation();
            mOrientationManager.stop();
            searchNearbyItems(location);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mLiveCard != null && mLiveCard.isPublished()) {
            mLiveCard.unpublish();
            mLiveCard.getSurfaceHolder().removeCallback(mRenderer);
            mLiveCard = null;
        }
        mOrientationManager = null;
        super.onDestroy();
    }

    private void searchNearbyItems(Location location) {
        mRequestManager.doRequest().searchNearbyItems(location, RADIUS,
                new Response.Listener<List<Item>>() {
                    @Override
                    public void onResponse(List<Item> response) {
                        if (response.size() > 0) {
                            showLiveCard(response);
                            items = response;
                        }
                    }
                });
    }

    private void showLiveCard(List<Item> items) {
        if (mLiveCard == null) {
            Intent pendingIntent = new Intent(this, ItemsActivity.class);
            pendingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mRenderer.setItems(items);
            mRenderer.setPendingIntent(PendingIntent.getActivity(this, 0, pendingIntent, 0)); // no need on Real Glass device.
            mLiveCard = mTimelineManager.createLiveCard(LIVE_CARD_TAG);
            mLiveCard.setDirectRenderingEnabled(true).getSurfaceHolder().addCallback(mRenderer);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, pendingIntent, 0));
            mLiveCard.publish();
        }
    }
}