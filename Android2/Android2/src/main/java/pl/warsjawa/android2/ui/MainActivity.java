package pl.warsjawa.android2.ui;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pl.warsjawa.android2.R;
import pl.warsjawa.android2.ui.list.MeetupListFragment;
import pl.warsjawa.android2.ui.map.MeetupsMapFragment;

public class MainActivity extends BaseActivity {

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final DrawerLayout drawerLayout = findView(R.id.main_layout);
        if (drawerLayout != null) {
            initDrawerToggle(drawerLayout);
            initClickListeners(drawerLayout);

            if (savedInstanceState == null) {
                replaceMainFragment(new MeetupsMapFragment());
            }
        }
    }

    private void initDrawerToggle(DrawerLayout drawerLayout) {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initClickListeners(final DrawerLayout drawerLayout) {
        Button button1 = findView(R.id.main_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceMainFragment(new MeetupsMapFragment());
                drawerLayout.closeDrawers();
            }
        });
        Button button2 = findView(R.id.main_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceMainFragment(new MeetupListFragment());
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle != null && toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (toggle != null) {
            toggle.syncState();
        }
    }

    private void replaceMainFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.main_container, fragment);
        tx.commit();
    }
}
