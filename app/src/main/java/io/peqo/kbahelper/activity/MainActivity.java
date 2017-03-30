package io.peqo.kbahelper.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import io.peqo.kbahelper.R;
import io.peqo.kbahelper.activity.fragment.DepartmentOverviewFragment;
import io.peqo.kbahelper.activity.fragment.HomeFragment;
import io.peqo.kbahelper.database.SQLiteHandler;
import io.peqo.kbahelper.model.User;
import io.peqo.kbahelper.util.SessionManager;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private SQLiteHandler db;
    private SessionManager session;

    private TextView fullName;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        fullName = (TextView) header.findViewById(R.id.drawerTopName);
        email = (TextView) header.findViewById(R.id.drawerTopEmail);

        session = new SessionManager(getApplicationContext());
        db = new SQLiteHandler(getApplicationContext());

        new UpdateHeaderInformation().execute();

        HomeFragment homeFragment = new HomeFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, homeFragment);
        ft.commit();
    }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedFragment(item.getItemId());
        return true;
    }

    private void displaySelectedFragment(int itemId) {
        Fragment fragment = null;

        switch(itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_depts:
                fragment = new DepartmentOverviewFragment();
                break;
            case R.id.nav_logout:
                session.setLogin(false);
                db.deleteUser();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
        }

        if(fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private class UpdateHeaderInformation extends AsyncTask<Void, Void, User> {

        @Override
        protected User doInBackground(Void... voids) {
            return db.getUser();
        }

        @Override
        protected void onPostExecute(User user) {
            fullName.setText(user.firstName + " " + user.lastName);
            email.setText(user.email);
        }
    }
}
