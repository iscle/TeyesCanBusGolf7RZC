package com.syu.canbus.module;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;

public final class UiNotifyEvent implements Runnable {
    public static final Handler HANDLER_UI = new Handler(Looper.getMainLooper());
    private final ArrayList<IUiNotify> mUiNotifies = new ArrayList<>();
    private int mUpdateCode;

    public UiNotifyEvent(int updateCode) {
        this.mUpdateCode = updateCode;
    }

    public int getUpdateCode() {
        return this.mUpdateCode;
    }

    public void setUpdateCode(int updateCode) {
        this.mUpdateCode = updateCode;
    }

    public synchronized int size() {
        return this.mUiNotifies.size();
    }

    public synchronized void addNotify(IUiNotify notify) {
        if (notify != null) {
            if (!this.mUiNotifies.contains(notify)) {
                this.mUiNotifies.add(notify);
            }
        }
    }

    public synchronized void addNotify(IUiNotify notify, int onNotify) {
        if (notify != null) {
            if (!this.mUiNotifies.contains(notify)) {
                this.mUiNotifies.add(notify);
            }
            if (onNotify == 1) {
                notify.onNotify(this.mUpdateCode, null, null, null);
            }
        }
    }

    public synchronized void removeNotify(IUiNotify notify) {
        if (notify != null) {
            this.mUiNotifies.remove(notify);
        }
    }

    public synchronized void clearNotifies() {
        this.mUiNotifies.clear();
    }

    public synchronized void onNotify() {
        if (this.mUiNotifies.size() > 0) {
            HANDLER_UI.post(this);
        }
    }

    public synchronized void onNotify(int[] ints, float[] flts, String[] strs) {
        if (this.mUiNotifies.size() > 0) {
            HANDLER_UI.post(new NofityData(ints, flts, strs));
        }
    }

    private class NofityData implements Runnable {
        public float[] flts;
        public int[] ints;
        public String[] strs;

        public NofityData(int[] ints2, float[] flts2, String[] strs2) {
            this.ints = ints2;
            this.flts = flts2;
            this.strs = strs2;
        }

        public void run() {
            synchronized (UiNotifyEvent.this) {
                for (IUiNotify mUiNotify : UiNotifyEvent.this.mUiNotifies) {
                    mUiNotify.onNotify(UiNotifyEvent.this.mUpdateCode, this.ints, this.flts, this.strs);
                }
            }
        }
    }

    public synchronized void run() {
        for (IUiNotify mUiNotify : this.mUiNotifies) {
            mUiNotify.onNotify(this.mUpdateCode, null, null, null);
        }
    }
}
