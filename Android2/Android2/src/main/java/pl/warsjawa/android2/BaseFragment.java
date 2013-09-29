package pl.warsjawa.android2;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CoolApp) activity.getApplication()).inject(this);
    }
}
