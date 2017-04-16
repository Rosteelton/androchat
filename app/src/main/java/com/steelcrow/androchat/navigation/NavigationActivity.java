package com.steelcrow.androchat.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.steelcrow.androchat.FragmentAbout;
import com.steelcrow.androchat.R;
import com.steelcrow.androchat.chatRoom.ChatRoomFragment;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final static int MENU_DIALOGS = 0;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_dialogs:
                ChatRoomFragment dialogsFragment = ChatRoomFragment.newInstance("Диалоги");
                addFragment(dialogsFragment);
                break;
            case R.id.nav_settings:
                EmptyFragment settingsFragment = EmptyFragment.newInstance("Настройки");
                addFragment(settingsFragment);
                break;
            case R.id.nav_about:
                FragmentAbout aboutFragment = FragmentAbout.newInstance("О приложении");
                addFragment(aboutFragment);
                break;
            case R.id.nav_exit:
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Intent intent = getIntent();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView headerUserLoginField = (TextView) headerView.findViewById(R.id.text_view_user_login);
        headerUserLoginField.setText(intent.getStringExtra("Login"));

        if (savedInstanceState == null) {
            navigationView.getMenu().getItem(MENU_DIALOGS).setChecked(true);
            onNavigationItemSelected(navigationView.getMenu().getItem(MENU_DIALOGS));
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction = fragmentTransaction.replace(R.id.content_navigation, fragment);
        fragmentTransaction.commit();
    }
}
