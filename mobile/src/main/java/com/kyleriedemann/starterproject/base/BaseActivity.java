package com.kyleriedemann.starterproject.base;

import android.support.v7.app.AppCompatActivity;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by kyle on 4/8/16.
 *
 * Base activity that manages a composite subscription that will unsubscribe ondestroy
 *
 * @author kylealanr
 */
public class BaseActivity extends AppCompatActivity {
    protected CompositeSubscription compositeSubscription = new CompositeSubscription();
    protected boolean isBackNav;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }

    /**
     * Helper method to enable Back navigation for a activity
     * When this method is called the activity will then display
     * a Back arrow that will navigate to the last item on the Stack
     */
    protected void enableBackNav() {
        if(getSupportActionBar() != null) {
            isBackNav = true;
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }
}
