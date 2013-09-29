package pl.warsjawa.android2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CoolApp) getApplication()).inject(this);
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
