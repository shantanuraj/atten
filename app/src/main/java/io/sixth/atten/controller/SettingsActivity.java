package io.sixth.atten.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.app_confirm)
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences preferences = getSharedPreferences(Atten.PREF_FILE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt(Atten.PREF_PRESENT, 0);
                        editor.putInt(Atten.PREF_ABSENT, 0);
                        editor.putInt(Atten.PREF_THRESHOLD, 50);
                        editor.putBoolean(Atten.PREF_FIRST_RUN, true);
                        editor.apply();
                    }
                })
                .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        hideActionBarTitle();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }
}
