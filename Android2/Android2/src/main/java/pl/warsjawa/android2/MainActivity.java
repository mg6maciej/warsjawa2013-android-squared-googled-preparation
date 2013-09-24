package pl.warsjawa.android2;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DrawerLayout drawerLayout = findView(R.id.main_layout);
    }
}
