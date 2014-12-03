package io.sixth.atten.controller;

import android.os.Bundle;

import io.sixth.atten.R;
import io.sixth.atten.util.BaseActivity;

/**
 * Created by walle on 04/12/14.
 */
public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBarTitle();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }
}
