package com.judaismproject.gloriane.judaismproject;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.judaismproject.gloriane.judaismproject.fragment.AboutFragment;
import com.judaismproject.gloriane.judaismproject.fragment.AnswerFragment;
import com.judaismproject.gloriane.judaismproject.fragment.BibliographyFragment;
import com.judaismproject.gloriane.judaismproject.fragment.MainFragment;
import com.judaismproject.gloriane.judaismproject.fragment.MangaFragment;

public class MainActivity extends AppCompatActivity {

    // Ints
    int currentPosition = 0;

    public static final String VISIBLE_FRAGMENT = "visible_fragment";

    // Drawer Layout
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // first load
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame_content, new AboutFragment());
        tx.commit();

        // drawer
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });


        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        setupDrawerContent(navigationView);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private class FragManager implements FragmentManager.OnBackStackChangedListener {

        public void onBackStackChanged() {
            FragmentManager fragMan = getSupportFragmentManager();
            Fragment fragment = fragMan.findFragmentByTag(VISIBLE_FRAGMENT);

            if (fragment instanceof AboutFragment) {
                currentPosition = 0;
            }
            if (fragment instanceof MangaFragment) {
                currentPosition = 1;
            }
            if (fragment instanceof MainFragment) {
                currentPosition = 2;
            }
            if (fragment instanceof AnswerFragment) {
                currentPosition = 2;
            }
            if (fragment instanceof BibliographyFragment) {
                currentPosition = 3;
            }
        }
    }


    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked

        Fragment fragment;

        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.about:
                fragmentClass = AboutFragment.class;
                break;
            case R.id.manga:
                fragmentClass = MangaFragment.class;
                break;
            case R.id.bibliography:
                fragmentClass = BibliographyFragment.class;
                break;
            case R.id.answer:
                fragmentClass = AnswerFragment.class;
                break;
            case R.id.quiz:
                fragmentClass = MainFragment.class;
                break;
            default:
                fragmentClass = MainFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_content, fragment, VISIBLE_FRAGMENT);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);

            // Set action bar title
            setTitle(menuItem.getTitle());

            // Close the navigation drawer
            drawerLayout.closeDrawers();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
