package com.syu.canbus.module.canbus;

import android.os.RemoteException;
import android.util.Log;

import com.syu.canbus.ConstGolf;
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

    private void addDoorNotify() {
        //DoorHelper.sUcDoorEngine = U_DOOR_ENGINE;
        //DoorHelper.sUcDoorFl = ConstGolf.U_DOOR_FL;
        //DoorHelper.sUcDoorFr = ConstGolf.U_DOOR_FR;
        //DoorHelper.sUcDoorRl = ConstGolf.U_DOOR_RL;
        //DoorHelper.sUcDoorRr = ConstGolf.U_DOOR_RR;
        //DoorHelper.sUcDoorBack = ConstGolf.U_DOOR_BACK;

        DoorHelper.getInstance().buildUi();
        for (int i = ConstGolf.U_DOOR_BEGIN; i < ConstGolf.U_DOOR_END; i++) {
            DataCanbus.NOTIFY_EVENTS[i].addNotify(DoorHelper.getInstance(), 0);
        }
    }

    private void removeDoorNotify() {
        for (int i = ConstGolf.U_DOOR_BEGIN; i < ConstGolf.U_DOOR_END; i++) {
            DataCanbus.NOTIFY_EVENTS[i].removeNotify(DoorHelper.getInstance());
        }
        DoorHelper.getInstance().destroyUi();
    }

    private void addAirNotify() {
        //AirHelper.getInstance().buildUi(new Air_0160_RZC_DaZhong_All(TheApp.getInstance()));
        for (int i = ConstGolf.U_AIR_BEGIN; i < ConstGolf.U_AIR_END; i++) {
            DataCanbus.NOTIFY_EVENTS[i].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        }
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_REAR].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_REAR_LOCK].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_REAR_AUTO].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_REAR_BLOW_FOOT].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_REAR_BLOW_BODY].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_REAR_WIND_LEVEL].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_CLEAR_AIR].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_SEAT_BLOW_RIGHT].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_SEAT_BLOW_LEFT].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_REAR_SEATHEAT_LEFT].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_REAR_SEATHEAT_RIGHT].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_NEWADD_AIR_STEER_SEAT_ON].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_CLEAR_AIR_PROGRESS].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_SHOW].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        for (int i = ConstGolf.U_AIR_REAR2; i <= ConstGolf.U_AIR_CYCLE2; i++) {
            DataCanbus.NOTIFY_EVENTS[i].addNotify(AirHelper.SHOW_AND_REFRESH, 0);
        }
    }

    private void removeAirNotify() {
        for (int i = ConstGolf.U_AIR_BEGIN; i < ConstGolf.U_AIR_END; i++) {
            DataCanbus.NOTIFY_EVENTS[i].removeNotify(AirHelper.SHOW_AND_REFRESH);
        }
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AIR_CLEAR_AIR].removeNotify(AirHelper.SHOW_AND_REFRESH);
        AirHelper.getInstance().destroyUi();
    }

    @Override
    public void in() {
        IModuleCallback callback = ModuleCallbackCanbus.getInstance();
        for (int i = 0; i < ConstGolf.U_CNT_MAX; i++) {
            DataCanbus.PROXY.register(callback, i, 1);
        }
        this.carId = (DataCanbus.DATA[FinalCanbus.U_CANBUS_ID] >> 16) & 0xFFFF;
        Log.d(TAG, "in: carId: " + this.carId);
        addDoorNotify();
        addAirNotify();
    }

    @Override
    public void out() {
        removeDoorNotify();
        removeAirNotify();
    }

    @Override
    public void update(int updateCode, int[] ints, float[] flts, String[] strs) throws RemoteException {
        if (updateCode < 0 || updateCode > ConstGolf.U_CNT_MAX) {
            Log.w(TAG, "update: Invalid update code (" + updateCode + ")");
            return;
        }

        switch (updateCode) {
            case ConstGolf.U_WARNNING_VEHICLE:
                warningVehicle(updateCode, ints);
                break;
            case ConstGolf.U_WARNNING_START_STOP:
                warningStartStop(updateCode, ints);
                break;
            case ConstGolf.U_WARNNING_CONV_CONSUMER:
                convConsumer(updateCode, ints);
                break;
            case ConstGolf.U_IDCARNUM:
                /*if (strs != null && strs.length >= 1 && !ToolkitMisc.strEqual(ConstGolf.mCarId, strs[0])) {
                    ConstGolf.mCarId = strs[0];
                    DataCanbus.NOTIFY_EVENTS[updateCode].onNotify();
                    break;
                }*/
                break;
            case ConstGolf.U_AIR_JUMPCON:
                HandlerCanbus.update(updateCode, ints);
                int value = DataCanbus.DATA[ConstGolf.U_AIR_JUMPCON];
                /*if (value == 1 && !Golf7AirActi.mIsFront) {
                    AirHelper.disableAirWindowLocal(true);
                    JumpPage.startActivity("com.syu.canbus", "com.syu.carinfo.golf7.Golf7AirActi");
                    break;
                } else if (value == 0 && Golf7AirActi.mIsFront && Golf7AirActi.mInstance != null) {
                    Golf7AirActi.mInstance.finish();
                    break;
                } else */
            {
                break;
            }
            case ConstGolf.U_JUMP_CARINFO:
                convDrivingMode(updateCode, ints);
                break;
            default:
                HandlerCanbus.update(updateCode, ints);
                break;
        }
    }

    private void convDrivingMode(int updateCode, int[] ints) {
        if (updateCode == ConstGolf.U_JUMP_CARINFO) {
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
        if (ints == null || ints.length < 2 || ints[0] < 0 || ints[0] >= ConstGolf.CONV_CONSUMER_MAX) {
            return;
        }
        //ConstGolf.mConvConsumer[ints[0]] = ints;
        DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, null, null);
    }

    private void warningVehicle(int updateCode, int[] ints) {
        if (ints == null || ints.length < 2 || ints[0] < 0 || ints[0] >= ConstGolf.VEHICLE_WARNING_MAX) {
            return;
        }
        //ConstGolf.mVehicleWarning[ints[0]] = ints;
        DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, null, null);
    }

    private void warningStartStop(int updateCode, int[] ints) {
        if (ints == null || ints.length < 2 || ints[0] < 0 || ints[0] >= ConstGolf.START_STOP_MAX) {
            return;
        }
        //ConstGolf.mSartStop[ints[0]] = ints;
        DataCanbus.NOTIFY_EVENTS[updateCode].onNotify(ints, null, null);
    }
}
