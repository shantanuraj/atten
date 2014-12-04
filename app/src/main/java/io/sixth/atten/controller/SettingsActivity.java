package io.sixth.atten.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.sixth.atten.R;
import io.sixth.atten.util.Atten;
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

    @OnClick(R.id.delete_button) void clickedDelete() {
        new MaterialDialog.Builder(this)
                .content(R.string.app_confirm)
                .theme(Theme.LIGHT)
                .positiveColorRes(R.color.material_deep_teal_900)
                .negativeColorRes(R.color.material_deep_teal_900)
                .positiveText(R.string.dialog_yes)
                .negativeText(R.string.dialog_no)
                .callback(new MaterialDialog.Callback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        SharedPreferences preferences = getSharedPreferences(Atten.PREF_FILE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt(Atten.PREF_PRESENT, 0);
                        editor.putInt(Atten.PREF_ABSENT, 0);
                        editor.putInt(Atten.PREF_THRESHOLD, 50);
                        editor.putBoolean(Atten.PREF_FIRST_RUN, true);
                        editor.apply();
                    }
                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }
}
