package io.sixth.atten.model;

import android.content.Context;
import android.content.SharedPreferences;

import io.sixth.atten.util.App;
import io.sixth.atten.util.Atten;

/**
 * Created by swapnil on 1/12/14.
 */
public class Attendance {
    private int presentDays;
    private int absentDays;
    private int threshold;
    private String title;

    private SharedPreferences mSharedPreferences;

    public Attendance(String title) {
        this.title = title;
        mSharedPreferences = App.getContext().getSharedPreferences(Atten.PREF_FILE + title, Context.MODE_PRIVATE);
        load();
    }

    public Attendance(int presentDays, int absentDays, int threshold) {
        this.presentDays = presentDays;
        this.absentDays = absentDays;
        this.threshold = threshold;
    }

    public Integer getPresentDays() {
        return this.presentDays;
    }

    public Integer getAbsentDays() {
        return this.absentDays;
    }

    public Integer getTotalDays() {
        return this.absentDays + this.presentDays;
    }

    public Double getAttendancePercent() {
        if (getTotalDays() == 0) {
            return 0.0;
        } else {
            return presentDays * 100.0 / (presentDays + absentDays);
        }
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public void markAbsentDay() {
        ++this.absentDays;
    }

    public void markPresentDay() {
        ++this.presentDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void load() {
        this.threshold = mSharedPreferences.getInt(Atten.PREF_THRESHOLD, 50);
        this.presentDays = mSharedPreferences.getInt(Atten.PREF_PRESENT, 0);
        this.absentDays = mSharedPreferences.getInt(Atten.PREF_ABSENT, 0);
    }

    public void save() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Atten.PREF_THRESHOLD, this.threshold);
        editor.putInt(Atten.PREF_PRESENT, this.presentDays);
        editor.putInt(Atten.PREF_ABSENT, this.absentDays);
        editor.apply();
    }

}
