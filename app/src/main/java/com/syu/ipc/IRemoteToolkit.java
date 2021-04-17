package com.syu.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteToolkit extends IInterface {
    IRemoteModule getRemoteModule(int i) throws RemoteException;

    abstract class Stub extends Binder implements IRemoteToolkit {
        private static final String DESCRIPTOR = "com.syu.ipc.IRemoteToolkit";
        private static final int TRANSACTION_getRemoteModule = 1;
        private static final int TRANSACTION_getDescriptor = 1598968902;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteToolkit asInterface(IBinder obj) {
            if (obj == null) return null;

            IInterface in = obj.queryLocalInterface(DESCRIPTOR);
            if (in instanceof IRemoteToolkit) {
                return (IRemoteToolkit) in;
            }

            return new Proxy(obj);
        }

        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_getRemoteModule:
                    data.enforceInterface(DESCRIPTOR);
                    IRemoteModule result = getRemoteModule(data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(result != null ? result.asBinder() : null);
                    return true;
                case TRANSACTION_getDescriptor:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IRemoteToolkit {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public IRemoteModule getRemoteModule(int moduleCode) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeInt(moduleCode);
                    mRemote.transact(TRANSACTION_getRemoteModule, data, reply, 0);
                    reply.readException();
                    return IRemoteModule.Stub.asInterface(reply.readStrongBinder());
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }
        }
    }
}
