package com.syu.canbus.module;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;

import com.syu.ipc.IRemoteToolkit;

import java.util.ArrayList;
import java.util.Random;

public class MsToolkitConnection implements ServiceConnection {
    private final Context mContext;
    private final ArrayList<ConnectionObserver> mConnectionObservers;
    private final Handler mHandler;

    private boolean mConnecting;
    private IRemoteToolkit mRemoteToolkit;

    private final Runnable mRunnableConnect = new Runnable() {
        public void run() {
            if (MsToolkitConnection.this.mRemoteToolkit == null) {
                Intent intent = new Intent("com.syu.ms.toolkit");
                intent.setComponent(new ComponentName("com.syu.ms", "app.ToolkitService"));
                mContext.bindService(intent, MsToolkitConnection.this, Context.BIND_AUTO_CREATE);
            }
        }
    };

    public MsToolkitConnection(Context context) {
        this.mContext = context;
        this.mConnectionObservers = new ArrayList<>();
        // HandlerThreads should be stopped, but since this class lives for the entire lifecycle
        // of the application, it's not needed.
        HandlerThread mHandlerThread = new HandlerThread("ConnectionThread");
        mHandlerThread.start();
        this.mHandler = new Handler(mHandlerThread.getLooper());
    }

    @Override
    public synchronized void onServiceConnected(ComponentName name, IBinder service) {
        mRemoteToolkit = IRemoteToolkit.Stub.asInterface(service);
        mConnecting = false;
        for (ConnectionObserver mConnectionObserver : this.mConnectionObservers) {
            mHandler.post(new OnServiceConnected(mConnectionObserver));
        }
    }

    @Override
    public synchronized void onServiceDisconnected(ComponentName name) {
        mRemoteToolkit = null;
        for (ConnectionObserver mConnectionObserver : this.mConnectionObservers) {
            mHandler.post(new OnServiceDisconnected(mConnectionObserver));
        }
        connect();
    }

    public IRemoteToolkit getRemoteToolkit() {
        return mRemoteToolkit;
    }

    public synchronized void connect() {
        if (!mConnecting && mRemoteToolkit == null) {
            mConnecting = true;
            mHandler.post(mRunnableConnect);
        }
    }

    public synchronized void addObserver(ConnectionObserver observer) {
        if (observer == null) return;
        if (mConnectionObservers.contains(observer)) return;

        mConnectionObservers.add(observer);
        if (mRemoteToolkit != null) {
            mHandler.post(new OnServiceConnected(observer));
        }
    }

    public synchronized void removeObserver(ConnectionObserver observer) {
        if (observer == null) return;

        this.mConnectionObservers.remove(observer);
        if (this.mRemoteToolkit != null) {
            this.mHandler.post(new OnServiceDisconnected(observer));
        }
    }

    public synchronized void clearObservers() {
        if (this.mRemoteToolkit != null) {
            for (ConnectionObserver mConnectionObserver : this.mConnectionObservers) {
                this.mHandler.post(new OnServiceDisconnected(mConnectionObserver));
            }
        }
        this.mConnectionObservers.clear();
    }

    private class OnServiceConnected implements Runnable {
        private final ConnectionObserver observer;

        OnServiceConnected(ConnectionObserver observer) {
            this.observer = observer;
        }

        public void run() {
            if (mRemoteToolkit != null && observer != null) {
                observer.onConnected(mRemoteToolkit);
            }
        }
    }

    private static class OnServiceDisconnected implements Runnable {
        private final ConnectionObserver observer;

        OnServiceDisconnected(ConnectionObserver observer) {
            this.observer = observer;
        }

        public void run() {
            if (observer != null) {
                observer.onDisconnected();
            }
        }
    }
}
