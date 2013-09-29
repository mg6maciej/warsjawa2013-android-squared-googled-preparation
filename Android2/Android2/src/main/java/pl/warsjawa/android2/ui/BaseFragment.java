package pl.warsjawa.android2.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;

import pl.warsjawa.android2.CoolApp;

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CoolApp) activity.getApplication()).inject(this);
    }
}
