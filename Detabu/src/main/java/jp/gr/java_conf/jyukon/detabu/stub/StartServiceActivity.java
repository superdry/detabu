package jp.gr.java_conf.jyukon.detabu.stub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import jp.gr.java_conf.jyukon.detabu.DetabuService;

public class StartServiceActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, DetabuService.class));
        finish();
    }
}