package com.kyleriedemann.starterproject.base;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleriedemann.starterproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.findById;

/**
 * Base activity with a navigation drawer. Extends BaseActivity to handle Rx CompositeSubscriptions.
 * Exposes the views of the navigation drawer to derived classes, and has a method to set the title
 * of the support toolbar provided.
 *
 * @author kylealanr
 */
public class BaseNavDrawerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    // nav drawer views
    protected ImageView navUserIcon;
    protected TextView navUserHeader;
    protected TextView navUserBody;
    protected DrawerLayout drawer;
    protected NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);

        navUserIcon = findById(navHeaderView, R.id.nav_user_icon);
        navUserHeader = findById(navHeaderView, R.id.nav_user_header);
        navUserBody = findById(navHeaderView, R.id.nav_user_body);

        navigationView.addHeaderView(navHeaderView);

        navUserHeader.setText("User");
        navUserBody.setText("user@domain.io");
    }

    @Override
    public void onBackPressed() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Sets the title of the toolbar provided by the base activity, if the toolbar is not null
     *
     * @param title the deisred title to set as the toolbar title
     */
    protected void setToolbarTitle(String title){
        if(toolbar != null)
            toolbar.setTitle(title);
    }
}
