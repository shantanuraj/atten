package io.sixth.atten.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import io.sixth.atten.util.App;
import io.sixth.atten.util.Atten;

/**
 * Created by walle on 03/02/15.
 */
public class Attendances {
    SharedPreferences mSharedPreferences;
    public Attendances() {
        mSharedPreferences = App.getContext().getSharedPreferences(Atten.PREF_GROUPS, Context.MODE_PRIVATE);
    }
    public HashMap<String, Attendance> mAttendances;
}
