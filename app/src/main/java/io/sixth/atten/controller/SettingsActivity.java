package io.sixth.atten.controller;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import butterknife.OnClick;
import io.sixth.atten.R;
import io.sixth.atten.util.BaseActivity;

/**
 * Created by walle on 04/12/14.
 */
public class SettingsActivity extends BaseActivity {

    @OnClick(R.id.atten_btn_shantanu) void clickedShantanu() {
        Uri url = Uri.parse("http://twitter.com/shantanuraj");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(url);
        startActivity(intent);
    }

    @OnClick(R.id.atten_btn_swapnil) void clickedSwapnil() {
        Uri url = Uri.parse("http://twitter.com/raj_swapnil");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(url);
        startActivity(intent);
    }

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
