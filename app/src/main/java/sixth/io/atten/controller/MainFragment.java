package sixth.io.atten.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import sixth.io.atten.R;
import sixth.io.atten.model.Attendance;
import sixth.io.atten.util.Atten;

/**
 * Created by swapnil on 1/12/14.
 */
public class MainFragment extends Fragment {

    private static SnackBar snackBar;
    private static Attendance attendance;
    private SharedPreferences preferences;

    public MainFragment() {
        attendance = new Attendance();
    }

    @InjectView(R.id.label_total_days) TextView totalDays;
    @InjectView(R.id.label_percentage) TextView percentage;
    @InjectView(R.id.label_present_days) TextView presentDays;
    @InjectView(R.id.label_absent_days) TextView absentDays;
    @InjectView(R.id.atten_threshold) SeekBar thresholdBar;

    @OnClick(R.id.atten_present) void presentHandler() {
        attendance.markPresentDay();
        refreshView();
    }

    @OnClick(R.id.atten_absent) void absentHandler() {
        attendance.markAbsentDay();
        refreshView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        thresholdBar.setOnSeekBarChangeListener(new ThresholdBar());
        snackBar = new SnackBar(getActivity());
        preferences = getActivity().getSharedPreferences(Atten.PREF_FILE, Context.MODE_PRIVATE);

        checkFirstRun();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshView();
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Atten.PREF_THRESHOLD, attendance.getThreshold());
        editor.putInt(Atten.PREF_PRESENT, attendance.getPresentDays());
        editor.putInt(Atten.PREF_ABSENT, attendance.getAbsentDays());
        editor.apply();
    }

    private void refreshView() {
        String percentageText;
        switch (attendance.getAttendancePercent().intValue()) {
            case 100: percentageText = "100%"; break;
            case 0: percentageText = ""; break;
            default:  percentageText = String.format("%.2f", attendance.getAttendancePercent().floatValue()) + "%";
        }
        String totalText = String.format("Days     : %d", attendance.getTotalDays());
        String presentText = String.format("Present : %d", attendance.getPresentDays());
        String absentText = String.format("Absent  : %d", attendance.getAbsentDays());

        thresholdBar.setProgress(attendance.getThreshold());
        totalDays.setText(totalText);
        presentDays.setText(presentText);
        absentDays.setText(absentText);
        percentage.setText(percentageText);

    }

    private void checkFirstRun() {
        if (preferences.getBoolean(Atten.PREF_FIRST_RUN, true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(Atten.PREF_FIRST_RUN, false);
            editor.apply();
        } else {
            int threshold = preferences.getInt(Atten.PREF_THRESHOLD, 50);
            int presentDays = preferences.getInt(Atten.PREF_PRESENT, 0);
            int absentDays = preferences.getInt(Atten.PREF_ABSENT, 0);
            attendance = new Attendance(presentDays, absentDays, threshold);
        }
    }

    static class ThresholdBar implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            attendance.setThreshold(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            snackBar.show("Threshold: " + attendance.getThreshold(), SnackBar.SHORT_SNACK);
        }
    }
}