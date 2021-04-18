package com.syu.canbus.module.canbus;

import android.os.RemoteException;
import android.util.Log;

import com.syu.canbus.ui.AirHelper;
import com.syu.canbus.ui.DoorHelper;
import com.syu.ipc.IModuleCallback;

public class Callback_0160_RZC_XP1_DaZhong_GaoErFu7 extends CallbackCanbusBase {
    private static final String TAG = "Callback_0160_RZC_XP1_D";

    int carId;

    Runnable mDismissFunctionalDrivingInfo1 = new Runnable() {
        public void run() {
            /*if (Golf7FunctionalDrivingInfo1Acti.mIsFront && Golf7FunctionalDrivingInfo1Acti.mInstance != null) {
                Golf7FunctionalDrivingInfo1Acti.mInstance.finish();
            }*/
        }
    };

    @Override
    public void in() {
        IModuleCallback callback = ModuleCallbackCanbusProxy.getInstance();
        for (int i = 0; i < 396; i++) {
            DataCanbus.PROXY.register(callback, i, 1);
        }
        this.carId = (DataCanbus.DATA[1000] >> 16) & 0xFFFF;
        Log.d(TAG, "in: carId: " + this.carId);
        //DoorHelper.sUcDoorEngine = 109;
        //DoorHelper.sUcDoorFl = 110;
        //DoorHelper.sUcDoorFr = 111;
        //DoorHelper.sUcDoorRl = 112;
        //DoorHelper.sUcDoorRr = 113;
        //DoorHelper.sUcDoorBack = 114;
        //AirHelper.getInstance().buildUi(new Air_0160_RZC_DaZhong_All(TheApp.getInstance()));
        for (int i7 = 87; i7 < 108; i7++) {
            DataCanbus.NOTIFY_EVENTS[i7].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        }
        DataCanbus.NOTIFY_EVENTS[123].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[151].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[207].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[204].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[206].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[205].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[190].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[191].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[192].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[220].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[221].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[366].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[226].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[158].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        for (int i8 = 150; i8 <= 157; i8++) {
            DataCanbus.NOTIFY_EVENTS[i8].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        }

        DoorHelper.getInstance().buildUi();
        for (int i9 = 109; i9 < 115; i9++) {
            DataCanbus.NOTIFY_EVENTS[i9].addNotify(DoorHelper.getInstance(), 0);
        }
    }

    @Override // com.syu.module.canbus.CallbackCanbusBase
    public void out() {
        for (int i = 109; i < 115; i++) {
            DataCanbus.NOTIFY_EVENTS[i].removeNotify(DoorHelper.getInstance());
        }
        for (int i2 = 87; i2 < 108; i2++) {
            DataCanbus.NOTIFY_EVENTS[i2].removeNotify(AirHelper.SHOW_AND_REFRESH);
        }
        DataCanbus.NOTIFY_EVENTS[190].removeNotify(AirHelper.SHOW_AND_REFRESH);
        AirHelper.getInstance().destroyUi();
        DoorHelper.getInstance().destroyUi();
    }

    @Override // com.syu.ipc.IModuleCallback
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        if (updateCode >= 0) {
            switch (updateCode) {
                case 74:
                    warningVehicle(updateCode, ints);
                    return;
                case 75:
                    warningStartStop(updateCode, ints);
                    return;
                case 76:
                    convConsumer(updateCode, ints);
                    return;
                case 82:
                    /*if (strs != null && strs.length >= 1 && !ToolkitMisc.strEqual(ConstGolf.mCarId, strs[0])) {
                        ConstGolf.mCarId = strs[0];
                        DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
                        return;
                    }8*/
                    return;
                case 104:
                    if (updateCode >= 0 && updateCode < 396) {
                        HandlerCanbus.update(updateCode, ints);
                    }
                    int value = DataCanbus.DATA[104];
                    /*if (value == 1 && !Golf7AirActi.mIsFront) {
                        AirHelper.disableAirWindowLocal(true);
                        JumpPage.startActivity("com.syu.canbus", "com.syu.carinfo.golf7.Golf7AirActi");
                        return;
                    } else if (value == 0 && Golf7AirActi.mIsFront && Golf7AirActi.mInstance != null) {
                        Golf7AirActi.mInstance.finish();
                        return;
                    } else */{
                        return;
                    }
                case 138:
                    convDrivingMode(updateCode, ints);
                    return;
                default:
                    if (updateCode >= 0 && updateCode < 396) {
                        HandlerCanbus.update(updateCode, ints);
                        return;
                    }
                    return;
            }
        }
    }

    private void convDrivingMode(int updateCode, int[] ints) {
        if (updateCode == 138) {
            HandlerCanbus.update(updateCode, ints);
            if (ints[0] != 0) {
                /*if (!Golf7FunctionalDrivingInfo1Acti.mIsFront) {
                    JumpPage.startActivity("com.syu.canbus", "com.syu.carinfo.golf7.Golf7FunctionalDrivingInfo1Acti");
                }
                HandlerUI.getInstance().removeCallbacks(this.mDismissFunctionalDrivingInfo1);
                HandlerUI.getInstance().postDelayed(this.mDismissFunctionalDrivingInfo1, 100000);*/
            }
        }
    }

    private void convConsumer(int updateCode, int[] ints) {
        if (ints != null && ints.length >= 2 && ints[0] >= 0 && ints[0] < 7) {
            //ConstGolf.mConvConsumer[ints[0]] = ints;
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, null, null);
        }
    }

    private void warningVehicle(int updateCode, int[] ints) {
        if (ints != null && ints.length >= 2 && ints[0] >= 0 && ints[0] < 16) {
            //ConstGolf.mVehicleWarning[ints[0]] = ints;
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, null, null);
        }
    }

    private void warningStartStop(int updateCode, int[] ints) {
        if (ints != null && ints.length >= 2 && ints[0] >= 0 && ints[0] < 7) {
            //ConstGolf.mSartStop[ints[0]] = ints;
            DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, null, null);
        }
    }
}
