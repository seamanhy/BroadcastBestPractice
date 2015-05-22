package cn.com.rjhn.broadcastbestpractice;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Carl on 2015/5/21.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }
}
