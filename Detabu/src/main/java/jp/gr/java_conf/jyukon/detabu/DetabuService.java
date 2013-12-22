package jp.gr.java_conf.jyukon.detabu;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import com.android.volley.Response;
import com.google.android.glass.sample.compass.OrientationManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jp.gr.java_conf.jyukon.detabu.model.Item;
import lombok.Getter;

public class DetabuService extends DetabuBaseService {

    private static final String LIVE_CARD_ID = "detabu";
    final int MAX_IMAGES = 3;

    public class DetabuBinder extends Binder {
        DetabuService getService() {
            return DetabuService.this;
        }
    }

    private final DetabuBinder mBinder = new DetabuBinder();
    @Inject OrientationManager mOrientationManager;
    @Inject ITimelineManager mTimelineManager;
    private ILiveCard mLiveCard;
    @Inject ICard mCard;
    @Inject RequestManager mRequestManager;
    @Getter private List<Item> items;

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
            mLiveCard = null;
        }
        mOrientationManager = null;
        super.onDestroy();
    }

    private void searchNearbyItems(Location location) {
        final int RADIUS = 500;
        mRequestManager.doRequest().searchNearbyItems(location, RADIUS,
                new Response.Listener<List<Item>>() {
                    @Override
                    public void onResponse(List<Item> response) {
                        if (response.size() > 0) {
                            showLiveCard(response.size());
                            for (Item item : response.subList(0, MAX_IMAGES)) {
                                addImage(item.getImageUrl());
                            }
                            items = response;
                        }
                    }
                });
    }

    private void addImage(final String imageUrl) {
        if (!isExternalStorageWritable())
            return;
        mRequestManager.doRequest().getImage(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        File file = new File(getExternalFilesDir(null), Uri.parse(imageUrl).getLastPathSegment());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (mCard.getNumberOfImages() >= MAX_IMAGES)
                            mCard.clearImages();
                        mCard.addImage(Uri.parse(file.toString()));
                        if (mLiveCard.isPublished())
                            mLiveCard.unpublish();
                        mLiveCard.publish();
                    }
                });
    }

    private boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private void showLiveCard(int itemCount) {
        if (mLiveCard == null) {
            mLiveCard = mTimelineManager.getLiveCard(LIVE_CARD_ID);
            mLiveCard.setNonSilent(true);
            mCard.setText(getString(R.string.item_found_message, itemCount));
            mCard.clearImages();
            mLiveCard.setViews(mCard.toRemoteViews());
            Intent pendingIntent = new Intent(this, ItemsActivity.class);
            pendingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, pendingIntent, 0));
            mLiveCard.publish();
        }
    }
}