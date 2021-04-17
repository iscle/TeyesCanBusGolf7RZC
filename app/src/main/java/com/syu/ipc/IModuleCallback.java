package com.syu.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IModuleCallback extends IInterface {
    void update(int i, int[] iArr, float[] fArr, String[] strArr) throws RemoteException;

    abstract class Stub extends Binder implements IModuleCallback {
        private static final String DESCRIPTOR = "com.syu.ipc.IModuleCallback";
        private static final int TRANSACTION_update = 1;
        private static final int TRANSACTION_getDescriptor = 1598968902;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IModuleCallback asInterface(IBinder obj) {
            if (obj == null) return null;

            IInterface in = obj.queryLocalInterface(DESCRIPTOR);
            if (in instanceof IModuleCallback) {
                return (IModuleCallback) in;
            }

            return new Proxy(obj);
        }

        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_update:
                    data.enforceInterface(DESCRIPTOR);
                    update(data.readInt(), data.createIntArray(), data.createFloatArray(), data.createStringArray());
                    return true;
                case TRANSACTION_getDescriptor:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IModuleCallback {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
                Parcel data = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeInt(updateCode);
                    data.writeIntArray(ints);
                    data.writeFloatArray(flts);
                    data.writeStringArray(strs);
                    mRemote.transact(TRANSACTION_update, data, null, FLAG_ONEWAY);
                } finally {
                    data.recycle();
                }
            }
        }
    }
}
