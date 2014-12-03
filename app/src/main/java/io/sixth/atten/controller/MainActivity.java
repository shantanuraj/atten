package io.sixth.atten.controller;

import android.os.Bundle;

import io.sixth.atten.R;
import io.sixth.atten.util.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBarTitle();
        setActionBarIcon(R.drawable.ic_atten_menu);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }

    public int getLayout(){
        return (R.layout.activity_main);
    }

}
