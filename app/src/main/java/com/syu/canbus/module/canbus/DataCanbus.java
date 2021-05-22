package com.syu.canbus.module.canbus;

import com.syu.ipc.RemoteModuleProxy;
import com.syu.canbus.module.UiNotifyEvent;

public class DataCanbus {
    public static final int[] DATA = new int[FinalCanbus.U_CNT_MAX];
    public static final UiNotifyEvent[] NOTIFY_EVENTS = new UiNotifyEvent[FinalCanbus.U_CNT_MAX];
    public static final RemoteModuleProxy PROXY = new RemoteModuleProxy();

    static {
        for (int i = 0; i < FinalCanbus.U_CNT_MAX; i++) {
            NOTIFY_EVENTS[i] = new UiNotifyEvent(i);
        }
        DATA[FinalCanbus.U_AIR_WINDOW_ENABLE] = 1;
        DATA[FinalCanbus.U_DOOR_WINDOW_ENABLE] = 1;
        //DoorHelper.doorWindowEnable(1);
        //AirHelper.airWindowEnable(1);
    }
}
