package pl.warsjawa.android2;

import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class BaseActivity extends ActionBarActivity {

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
