package jp.gr.java_conf.jyukon.detabu;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemsActivity extends DetabuBaseActivity {
    private static final String TAG_ITEM_SCROLL = "ItemScrollFragment";
    private static final String TAG_ITEM_DIRECTION = "ItemDirectionFragment";
    @InjectView(R.id.container1) FrameLayout container1;
    @InjectView(R.id.container2) FrameLayout container2;
    DetabuService mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            DetabuService.DetabuBinder binder = (DetabuService.DetabuBinder) service;
            mService = binder.getService();
            showItemScrollFragment();
//            showItemDirectionFragment();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_activity);
        ButterKnife.inject(this);
        bindService(new Intent(this, DetabuService.class), mConnection, 0);
    }

    private void showItemScrollFragment() {
        ItemScrollFragment fragment = (ItemScrollFragment) getSupportFragmentManager().findFragmentByTag(TAG_ITEM_SCROLL);
        if (fragment == null)
            fragment = ItemScrollFragment.newInstance(mService.getItems());
        getSupportFragmentManager().beginTransaction().replace(R.id.container1, fragment, TAG_ITEM_SCROLL).commit();
    }

    private void showItemDirectionFragment() {
        ItemDirectionFragment fragment = (ItemDirectionFragment) getSupportFragmentManager().findFragmentByTag(TAG_ITEM_DIRECTION);
        if (fragment == null)
            fragment = ItemDirectionFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container2, fragment, TAG_ITEM_DIRECTION).commit();
    }
}