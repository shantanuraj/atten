package io.sixth.atten.util;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import io.sixth.atten.R;

/**
 * Created by walle on 23/10/14.
 */
public abstract class BaseActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            hideActionBarTitle();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    abstract public int getLayout();


    protected void hideActionBarTitle() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void hideActionBarHomeButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

}