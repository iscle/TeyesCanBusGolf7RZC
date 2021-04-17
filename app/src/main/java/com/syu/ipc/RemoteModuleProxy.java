package com.syu.ipc;

import android.os.RemoteException;

public class RemoteModuleProxy extends IRemoteModule.Stub {
    private IRemoteModule mRemoteModule;

    public IRemoteModule getRemoteModule() {
        return this.mRemoteModule;
    }

    public void setRemoteModule(IRemoteModule remoteModule) {
        this.mRemoteModule = remoteModule;
    }

    @Override
    public void cmd(int cmdCode, int[] ints, float[] flts, String[] strs) {
        if (mRemoteModule == null) return;
        try {
            mRemoteModule.cmd(cmdCode, ints, flts, strs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cmd(int cmdCode) {
        if (mRemoteModule == null) return;
        try {
            mRemoteModule.cmd(cmdCode, null, null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cmd(int cmdCode, int value) {
        if (mRemoteModule == null) return;
        try {
            mRemoteModule.cmd(cmdCode, new int[]{value}, null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cmd(int cmdCode, int value1, int value2) {
        if (mRemoteModule == null) return;
        try {
            mRemoteModule.cmd(cmdCode, new int[]{value1, value2}, null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ModuleObject get(int getCode, int[] ints, float[] flts, String[] strs) {
        if (mRemoteModule == null) return null;
        try {
            return mRemoteModule.get(getCode, ints, flts, strs);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ModuleObject get(int getCode, int value) {
        if (mRemoteModule == null) return null;
        try {
            return mRemoteModule.get(getCode, new int[]{value}, null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getI(int getCode, int valueIfNotOk) {
        if (mRemoteModule == null) return valueIfNotOk;
        try {
            ModuleObject obj = mRemoteModule.get(getCode, null, null, null);
            if (obj == null || obj.ints == null || obj.ints.length < 1) {
                return valueIfNotOk;
            }
            return obj.ints[0];
        } catch (RemoteException e) {
            e.printStackTrace();
            return valueIfNotOk;
        }
    }

    public String getS(int getCode, int value) {
        if (mRemoteModule == null) return null;
        try {
            ModuleObject obj = mRemoteModule.get(getCode, new int[]{value}, null, null);
            if (obj == null || obj.strs == null || obj.strs.length < 1) {
                return null;
            }
            return obj.strs[0];
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getS(int getCode, int value1, int value2) {
        if (mRemoteModule == null) return null;
        try {
            ModuleObject obj = mRemoteModule.get(getCode, new int[]{value1, value2}, null, null);
            if (obj == null || obj.strs == null || obj.strs.length < 1) {
                return null;
            }
            return obj.strs[0];
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void register(IModuleCallback callback, int updateCode, int update) {
        if (mRemoteModule == null) return;
        try {
            mRemoteModule.register(callback, updateCode, update);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unregister(IModuleCallback callback, int updateCode) {
        if (mRemoteModule == null) return;
        try {
            mRemoteModule.unregister(callback, updateCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
