package com.syu.canbus.module;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

public final class UiNotifyEvent {
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
        if (notify == null) return;
        if (this.mUiNotifies.contains(notify)) return;
        this.mUiNotifies.add(notify);
    }

    public synchronized void addNotify(IUiNotify notify, int onNotify) {
        if (notify == null) return;
        if (!this.mUiNotifies.contains(notify)) {
            this.mUiNotifies.add(notify);
        }
        if (onNotify == 1) {
            notify.onNotify(this.mUpdateCode, null, null, null);
        }
    }

    public synchronized void removeNotify(IUiNotify notify) {
        if (notify == null) return;
        this.mUiNotifies.remove(notify);
    }

    public synchronized void clearNotifies() {
        this.mUiNotifies.clear();
    }

    public synchronized void onNotify() {
        onNotify(null, null, null);
    }

    public synchronized void onNotify(int[] ints, float[] flts, String[] strs) {
        if (this.mUiNotifies.isEmpty()) return;
        HANDLER_UI.post(new NotifyData(ints, flts, strs));
    }

    private class NotifyData implements Runnable {
        public float[] flts;
        public int[] ints;
        public String[] strs;

        public NotifyData(int[] ints, float[] flts, String[] strs) {
            this.ints = ints;
            this.flts = flts;
            this.strs = strs;
        }

        public void run() {
            synchronized (UiNotifyEvent.this) {
                for (IUiNotify mUiNotify : UiNotifyEvent.this.mUiNotifies) {
                    mUiNotify.onNotify(UiNotifyEvent.this.mUpdateCode, this.ints, this.flts, this.strs);
                }
            }
        }
    }
}
