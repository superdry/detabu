package jp.gr.java_conf.jyukon.detabu;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class ItemsActivity extends DetabuBaseActivity {
    private ItemScrollFragment itemScrollFragment;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            DetabuService.DetabuBinder binder = (DetabuService.DetabuBinder) service;
            itemScrollFragment.setItems(binder.getService().getItems());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_activity);
        itemScrollFragment = (ItemScrollFragment)getSupportFragmentManager().findFragmentById(R.id.item_scroll_fragment);
        bindService(new Intent(this, DetabuService.class), mConnection, 0);
    }
}