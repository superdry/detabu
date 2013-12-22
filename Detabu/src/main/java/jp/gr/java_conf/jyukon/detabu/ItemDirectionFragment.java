package jp.gr.java_conf.jyukon.detabu;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.glass.sample.compass.OrientationManager;
import com.google.android.glass.sample.compass.util.MathUtils;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.gr.java_conf.jyukon.detabu.event.ItemChangedEvent;
import jp.gr.java_conf.jyukon.detabu.event.ItemSelectedEvent;
import jp.gr.java_conf.jyukon.detabu.model.Item;

public class ItemDirectionFragment extends DetabuBaseFragment {

    private static final long START_DURATION_MILLIS = 400;
    private static final long DURATION_MILLIS = 10000;
    private static final long DELAY_MILLIS = 1000;
    private static final long BLINK_DURATION_MILLIS = 600;
    private static final long BLINK_DELAY_MILLIS = 100;
    private static final long TOLERANCE_DEGREES = 5;
    @Inject OrientationManager mOrientationManager;
    @InjectView(R.id.container) RelativeLayout container;
    @InjectView(R.id.left_arrow1) ImageView leftArrow1;
    @InjectView(R.id.left_arrow2) ImageView leftArrow2;
    @InjectView(R.id.left_arrow3) ImageView leftArrow3;
    @InjectView(R.id.right_arrow1) ImageView rightArrow1;
    @InjectView(R.id.right_arrow2) ImageView rightArrow2;
    @InjectView(R.id.right_arrow3) ImageView rightArrow3;
    @InjectView(R.id.distance) TextView distanceText;
    @InjectView(R.id.unit) TextView unitText;

    private Item mItem;
    private AnimatorSet leftBlinker;
    private AnimatorSet rightBlinker;
    private boolean mRunning = false;
    private final Handler mHandler = new Handler();
    private final Runnable mUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            if (mRunning) {
                mHandler.postDelayed(mUpdateRunnable, DELAY_MILLIS);
                update();
            }
        }
    };
    private final Runnable mStopRunnable = new Runnable() {
        @Override
        public void run() {
            stop();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_direction_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareAnimation();
    }

    @Subscribe
    public void onItemChanged(ItemChangedEvent event) {
        mItem = event.getItem();
        stop();
    }

    @Subscribe
    public void onItemSelected(ItemSelectedEvent event) {
        start();
    }

    private void start() {
        if (mRunning)
            return;
        mRunning = true;
        mOrientationManager.start();
        mHandler.post(mUpdateRunnable);
        mHandler.postDelayed(mStopRunnable, DURATION_MILLIS);
        container.animate().alpha(1);
    }

    private void stop() {
        if (mRunning) {
            mOrientationManager.stop();
            mHandler.removeCallbacks(mUpdateRunnable);
            mHandler.removeCallbacks(mStopRunnable);
            mRunning = false;
            container.animate().alpha(0);
        }
    }

    private void update() {
        if (mOrientationManager.hasLocation()) {
            Location location = mOrientationManager.getLocation();
            float distance = MathUtils.getDistance(location.getLatitude(),location.getLongitude(), mItem.getPlaceGeo().getLat(), mItem.getPlaceGeo().getLon());
            if (distance > 1) {
                distanceText.setText(String.format("%.1f", distance));
                unitText.setText("Km");
            } else {
                distanceText.setText(String.format("%d", Math.round(distance * 1000)));
                unitText.setText("m");
            }

            float targetBearing = MathUtils.getBearing(location.getLatitude(),location.getLongitude(), mItem.getPlaceGeo().getLat(), mItem.getPlaceGeo().getLon());
            float bearing = mOrientationManager.getHeading();
            float delta = (targetBearing - bearing + 360) % 360;
            if (delta > TOLERANCE_DEGREES && delta < 180) {
                rightBlinker.start();
            } else if (delta >= 180 && delta < 360 - TOLERANCE_DEGREES) {
                leftBlinker.start();
            }
        } else {
            distanceText.setText("???m");
        }
    }

    private void prepareAnimation() {
        container.animate().setDuration(START_DURATION_MILLIS);

        Keyframe kf1 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf2 = Keyframe.ofFloat(.5f, 1f);
        Keyframe kf3 = Keyframe.ofFloat(1f, 0f);
        kf2.setInterpolator(new AccelerateDecelerateInterpolator());
        kf3.setInterpolator(new AccelerateDecelerateInterpolator());
        PropertyValuesHolder blink = PropertyValuesHolder.ofKeyframe("alpha", kf1, kf2, kf3);

        leftBlinker = createBlinkerAnimatorSet(blink, leftArrow1, leftArrow2, leftArrow3);
        rightBlinker = createBlinkerAnimatorSet(blink, rightArrow1, rightArrow2, rightArrow3);
    }

    private AnimatorSet createBlinkerAnimatorSet(PropertyValuesHolder blink, View v1, View v2, View v3) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator blinker1 = ObjectAnimator.ofPropertyValuesHolder(v1, blink).setDuration(BLINK_DURATION_MILLIS);
        ObjectAnimator blinker2 = ObjectAnimator.ofPropertyValuesHolder(v2, blink).setDuration(BLINK_DURATION_MILLIS);
        blinker2.setStartDelay(BLINK_DELAY_MILLIS);
        ObjectAnimator blinker3 = ObjectAnimator.ofPropertyValuesHolder(v3, blink).setDuration(BLINK_DURATION_MILLIS);
        blinker3.setStartDelay(BLINK_DELAY_MILLIS * 2);
        animatorSet.playTogether(blinker1, blinker2, blinker3);
        return animatorSet;
    }
}
