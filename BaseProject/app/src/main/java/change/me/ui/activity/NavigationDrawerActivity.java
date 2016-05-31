package change.me.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.evgeniysharafan.utils.Fragments;

import butterknife.BindView;
import butterknife.ButterKnife;
import change.me.R;
import change.me.ui.fragment.FirstFragment;
import change.me.ui.fragment.SecondFragment;

public class NavigationDrawerActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private static final String STATE_SELECTED_ITEM_POSITION = "state_selected_item_position";
    private static final String STATE_SELECTED_ITEM_ID = "state_selected_item_id";
    private static final String TAG_FOR_FRAGMENT = "tag_for_fragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;
    private int selectedItemPosition;
    private int selectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        restoreState(savedInstanceState);
        setUpDrawer();
        setUpToolbar();
        setFragment();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            selectedItemPosition = savedInstanceState.getInt(STATE_SELECTED_ITEM_POSITION);
            selectedItemId = savedInstanceState.getInt(STATE_SELECTED_ITEM_ID);
        }
    }

    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };

        drawer.addDrawerListener(drawerToggle);
        navigationView.getMenu().getItem(selectedItemPosition).setChecked(true);

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUpToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Title");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            drawerToggle.syncState();
        }
    }

    private void setFragment() {
        Fragment existingFragment = Fragments.getByTag(getSupportFragmentManager(), TAG_FOR_FRAGMENT);
        if (existingFragment == null) {
            selectFragment(selectedItemId == 0 ? R.id.navigation_item_0 : selectedItemId);
        }
    }

    private void selectFragment(@IdRes int id) {
        if (selectedItemId == id) {
            return;
        }

        Fragments.popEntireBackStackImmediate(getSupportFragmentManager());
        Fragments.replace(getSupportFragmentManager(), R.id.content, getFragment(id), TAG_FOR_FRAGMENT);
    }

    private Fragment getFragment(@IdRes int id) {
        Fragment result;

        if (id == R.id.navigation_item_0) {
            selectedItemPosition = 0;
            result = FirstFragment.newInstance();
        } else if (id == R.id.navigation_item_1) {
            selectedItemPosition = 1;
            result = SecondFragment.newInstance();
        } else {
            throw new IllegalArgumentException("Fragment with id = " + id + ", is not defined");
        }

        selectedItemId = id;

        return result;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        int id = menuItem.getItemId();
        selectFragment(id);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public boolean isDrawerOpen() {
        return drawer != null && drawer.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (isDrawerOpen()) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_ITEM_POSITION, selectedItemPosition);
        outState.putInt(STATE_SELECTED_ITEM_ID, selectedItemId);
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
