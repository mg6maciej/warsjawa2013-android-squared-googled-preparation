package pl.warsjawa.android2.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import pl.warsjawa.android2.CoolApp;

public class BaseFragment extends Fragment {

    @Inject
    Bus bus;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CoolApp) activity.getApplication()).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.register(this);
    }
}
