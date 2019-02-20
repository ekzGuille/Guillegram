package com.android.gss.guillegram.views;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.gss.guillegram.R;
import com.android.gss.guillegram.views.fragments.HomeFragment;
import com.android.gss.guillegram.views.fragments.ProfileFragment;
import com.android.gss.guillegram.views.fragments.SearchFragment;


public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomNavigationView tabMenu = findViewById(R.id.bottombar);

        tabMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_tab:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new HomeFragment())
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;

                    case R.id.profile_tab:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new ProfileFragment())
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;

                    case R.id.search_tab:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new SearchFragment())
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                }
                return true;
            }
        });

        tabMenu.setSelectedItemId(R.id.home_tab);

    }


}
