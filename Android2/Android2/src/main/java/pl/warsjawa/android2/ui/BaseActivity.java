package pl.warsjawa.android2.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import javax.inject.Inject;

import pl.warsjawa.android2.CoolApp;
import pl.warsjawa.android2.event.EventBus;

public class BaseActivity extends ActionBarActivity {

    @Inject
    EventBus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CoolApp) getApplication()).inject(this);
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }
}
