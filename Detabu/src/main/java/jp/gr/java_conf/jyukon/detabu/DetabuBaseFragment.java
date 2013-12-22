package jp.gr.java_conf.jyukon.detabu;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.squareup.otto.Bus;

import javax.inject.Inject;

public class DetabuBaseFragment extends Fragment {
    @Inject Bus bus;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((DetabuBaseActivity) activity).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }
}
