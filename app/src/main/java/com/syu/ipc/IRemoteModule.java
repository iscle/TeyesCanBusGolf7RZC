package com.syu.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteModule extends IInterface {
    void cmd(int i, int[] iArr, float[] fArr, String[] strArr) throws RemoteException;

    ModuleObject get(int i, int[] iArr, float[] fArr, String[] strArr) throws RemoteException;

    void register(IModuleCallback iModuleCallback, int i, int i2) throws RemoteException;

    void unregister(IModuleCallback iModuleCallback, int i) throws RemoteException;

    abstract class Stub extends Binder implements IRemoteModule {
        private static final String DESCRIPTOR = "com.syu.ipc.IRemoteModule";
        private static final int TRANSACTION_cmd = 1;
        private static final int TRANSACTION_get = 2;
        private static final int TRANSACTION_register = 3;
        private static final int TRANSACTION_unregister = 4;
        private static final int TRANSACTION_getDescriptor = 1598968902;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteModule asInterface(IBinder obj) {
            if (obj == null) return null;

            IInterface in = obj.queryLocalInterface(DESCRIPTOR);
            if (in instanceof IRemoteModule) {
                return (IRemoteModule) in;
            }

            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_cmd:
                    data.enforceInterface(DESCRIPTOR);
                    cmd(data.readInt(), data.createIntArray(), data.createFloatArray(), data.createStringArray());
                    return true;
                case TRANSACTION_get:
                    data.enforceInterface(DESCRIPTOR);
                    ModuleObject result = get(data.readInt(), data.createIntArray(), data.createFloatArray(), data.createStringArray());
                    reply.writeNoException();
                    if (result != null) {
                        reply.writeInt(1);
                        reply.writeIntArray(result.ints);
                        reply.writeFloatArray(result.flts);
                        reply.writeStringArray(result.strs);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_register:
                    data.enforceInterface(DESCRIPTOR);
                    register(IModuleCallback.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readInt());
                    return true;
                case TRANSACTION_unregister:
                    data.enforceInterface(DESCRIPTOR);
                    unregister(IModuleCallback.Stub.asInterface(data.readStrongBinder()), data.readInt());
                    return true;
                case TRANSACTION_getDescriptor:
                    reply.writeString(DESCRIPTOR);
                    return true;
            }

            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IRemoteModule {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void cmd(int cmdCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeInt(cmdCode);
                    data.writeIntArray(ints);
                    data.writeFloatArray(flts);
                    data.writeStringArray(strs);
                    mRemote.transact(TRANSACTION_cmd, data, reply, FLAG_ONEWAY);
                    reply.readException();
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            @Override
            public ModuleObject get(int getCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(Stub.DESCRIPTOR);
                    data.writeInt(getCode);
                    data.writeIntArray(ints);
                    data.writeFloatArray(flts);
                    data.writeStringArray(strs);
                    mRemote.transact(TRANSACTION_get, data, reply, 0);
                    reply.readException();
                    ModuleObject result;
                    if (reply.readInt() != 0) {
                        result = new ModuleObject();
                        result.ints = reply.createIntArray();
                        result.flts = reply.createFloatArray();
                        result.strs = reply.createStringArray();
                    } else {
                        result = null;
                    }
                    return result;
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            @Override
            public void register(IModuleCallback callback, int updateCode, int update) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(Stub.DESCRIPTOR);
                    data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    data.writeInt(updateCode);
                    data.writeInt(update);
                    mRemote.transact(TRANSACTION_register, data, reply, FLAG_ONEWAY);
                    reply.readException();
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            @Override
            public void unregister(IModuleCallback callback, int updateCode) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(Stub.DESCRIPTOR);
                    data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    data.writeInt(updateCode);
                    mRemote.transact(TRANSACTION_unregister, data, reply, FLAG_ONEWAY);
                    reply.readException();
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }
        }
    }
}
