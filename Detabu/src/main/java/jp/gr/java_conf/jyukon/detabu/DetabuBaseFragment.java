package jp.gr.java_conf.jyukon.detabu;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class DetabuBaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((DetabuBaseActivity) activity).inject(this);
    }
}
