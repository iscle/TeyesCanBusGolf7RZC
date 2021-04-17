package com.syu.canbus.util;

import android.os.Handler;

public final class HandlerHelper implements Runnable {
    private static final int MAX_POOL_SIZE = 50;
    private static HandlerHelper sPool;
    private static int sPoolSize = 0;
    private static final Object sPoolSync = new Object();
    private Runnable callback;
    private long delayMillis;
    private HandlerHelper next;
    private Handler target;

    public static void removeCallbacks(Handler target2, Runnable callback2) {
        if (target2 != null) {
            HandlerHelper helper = obtain();
            helper.target = target2;
            helper.callback = callback2;
            helper.delayMillis = -1;
            target2.post(helper);
        }
    }

    public static void postDelayed(Handler target2, Runnable callback2, long delayMillis2) {
        if (target2 != null) {
            HandlerHelper helper = obtain();
            helper.target = target2;
            helper.callback = callback2;
            if (delayMillis2 < 0) {
                delayMillis2 = 0;
            }
            helper.delayMillis = delayMillis2;
            target2.post(helper);
        }
    }

    private static HandlerHelper obtain() {
        synchronized (sPoolSync) {
            if (sPool == null) {
                return new HandlerHelper();
            }
            HandlerHelper helper = sPool;
            sPool = helper.next;
            helper.next = null;
            sPoolSize--;
            return helper;
        }
    }

    private void recycle() {
        this.target = null;
        this.callback = null;
        synchronized (sPoolSync) {
            if (sPoolSize < 50) {
                this.next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }

    private HandlerHelper() {
    }

    public void run() {
        if (this.delayMillis < 0) {
            this.target.removeCallbacks(this.callback);
        } else {
            this.target.postDelayed(this.callback, this.delayMillis);
        }
        recycle();
    }
}
