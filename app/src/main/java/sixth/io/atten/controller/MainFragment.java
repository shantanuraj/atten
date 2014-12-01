package sixth.io.atten.controller;

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

/**
 * Created by swapnil on 1/12/14.
 */
public class MainFragment extends Fragment {

    private static SnackBar snackBar;

    private Attendance attendance;
    private static int threshold;
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
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshView();
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

        totalDays.setText(totalText);
        presentDays.setText(presentText);
        absentDays.setText(absentText);
        percentage.setText(percentageText);

    }

    static class ThresholdBar implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            threshold = progress;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            snackBar.show("Threshold: " + threshold, SnackBar.SHORT_SNACK);
        }
    }
}