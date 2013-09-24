package pl.warsjawa.android2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final DrawerLayout drawerLayout = findView(R.id.main_layout);

        Button button1 = findView(R.id.main_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceInMainContainer(new MeetupsMapFragment());
                drawerLayout.closeDrawers();
            }
        });
        Button button2 = findView(R.id.main_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceInMainContainer(new Fragment());
                drawerLayout.closeDrawers();
            }
        });

        if (savedInstanceState == null) {
            replaceInMainContainer(new MeetupsMapFragment());
        }
    }

    private void replaceInMainContainer(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.main_container, fragment);
        tx.commit();
    }
}
