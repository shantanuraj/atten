package io.sixth.atten.model;

/**
 * Created by swapnil on 1/12/14.
 */
public class Attendance {
    private int presentDays;
    private int absentDays;
    private int threshold;

    public Attendance() {
        this.absentDays = this.presentDays = 0;
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

}
