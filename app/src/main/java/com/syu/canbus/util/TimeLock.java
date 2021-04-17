package com.syu.canbus.util;

import android.os.SystemClock;

public class TimeLock {
    private long cur;
    private long last;

    public boolean unlock(int ms) {
        this.cur = SystemClock.uptimeMillis();
        if (this.cur - this.last >= ((long) ms)) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.last = SystemClock.uptimeMillis();
    }

    public void zero() {
        this.last = 0;
    }
}
