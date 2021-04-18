package com.syu.canbus.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;

import com.syu.canbus.module.IUiNotify;
import com.syu.canbus.module.canbus.DataCanbus;
import com.syu.canbus.module.canbus.FinalCanbus;
import com.syu.canbus.util.HandlerUI;
import com.syu.canbus.util.SecondTickThread;

public class DoorHelper implements IUiNotify, Runnable {
    private static final DoorHelper INSTANCE = new DoorHelper();
    public static boolean sDisableDoorWindowLocal;
    private static int sDoorWindowEnable = 1;
    public static boolean sFlagShowDoorWindow = true;
    public static int sUcDoorBack = -1;
    public static int sUcDoorEngine = -1;
    public static int sUcDoorFl = -1;
    public static int sUcDoorFr = -1;
    public static int sUcDoorRl = -1;
    public static int sUcDoorRr = -1;
    private final Runnable SHOW = new Runnable() {
        /* class com.syu.ui.door.DoorHelper.AnonymousClass2 */

        public void run() {
            int doorstate;
            int doorstate2;
            int doorstate3;
            int doorstate4;
            int doorstate5;
            int doorstate6;
            if (DataCanbus.DATA[DoorHelper.sUcDoorEngine] == 0 && DataCanbus.DATA[DoorHelper.sUcDoorFl] == 0 && DataCanbus.DATA[DoorHelper.sUcDoorFr] == 0 && DataCanbus.DATA[DoorHelper.sUcDoorRl] == 0 && DataCanbus.DATA[DoorHelper.sUcDoorRr] == 0 && DataCanbus.DATA[DoorHelper.sUcDoorBack] == 0) {
                DoorHelper.this.mTick = 0;
                if (DoorHelper.this.mWindow.isShowing()) {
                    DoorHelper.this.mWindow.dismiss();
                }
                DataCanbus.PROXY.cmd(FinalCanbus.C_CANBUS_CAR_DOOR, new int[1], null, null);
                return;
            }
            if (DataCanbus.DATA[DoorHelper.sUcDoorEngine] == 1) {
                doorstate = 0 | 1;
            } else {
                doorstate = 0 & 254;
            }
            if (DataCanbus.DATA[DoorHelper.sUcDoorBack] == 1) {
                doorstate2 = doorstate | 2;
            } else {
                doorstate2 = doorstate & 253;
            }
            if (DataCanbus.DATA[DoorHelper.sUcDoorFl] == 1) {
                doorstate3 = doorstate2 | 4;
            } else {
                doorstate3 = doorstate2 & 251;
            }
            if (DataCanbus.DATA[DoorHelper.sUcDoorFr] == 1) {
                doorstate4 = doorstate3 | 8;
            } else {
                doorstate4 = doorstate3;// & FinalCanbus.CAR_WC2_XueTieLongC3XR;
            }
            if (DataCanbus.DATA[DoorHelper.sUcDoorRl] == 1) {
                doorstate5 = doorstate4 | 16;
            } else {
                doorstate5 = doorstate4 & 239;
            }
            if (DataCanbus.DATA[DoorHelper.sUcDoorRl] == 1) {
                doorstate6 = doorstate5 | 32;
            } else {
                doorstate6 = doorstate5 & 223;
            }
            DataCanbus.PROXY.cmd(FinalCanbus.C_CANBUS_CAR_DOOR, new int[]{doorstate6}, null, null);
            DoorHelper.this.mTick = 5;
            if (!DoorHelper.this.mWindow.isShowing()) {
                //TheApp.addRootView(DoorHelper.this.mWindow);
                //if (TheApp.rootViewWindowToken() == null) {
                //    HandlerUI.getInstance().postDelayed(this, 1);
                //    return;
                //}
                //TheApp.showWindow(DoorHelper.this.mWindow, 17, 0, 0);
            }
            View view = DoorHelper.this.mWindow.getContentView();
            if (view != null) {
                view.invalidate();
            }
        }
    };
    private Runnable mHideWindow = new Runnable() {
        /* class com.syu.ui.door.DoorHelper.AnonymousClass1 */

        public void run() {
            DoorHelper.this.mWindow.dismiss();
        }
    };
    private int mTick;
    private PopupWindow mWindow;

    public PopupWindow getWindow() {
        return this.mWindow;
    }

    public void initWindow(Context context) {
        if (this.mWindow == null) {
            SecondTickThread.getInstance().addTick(this);
            this.mWindow = new PopupWindow(context);
            this.mWindow.setWidth(-2);
            this.mWindow.setHeight(-2);
            this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.mWindow.setOutsideTouchable(true);
            this.mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                public void onDismiss() {
                    //TheApp.removeRootView(DoorHelper.this.mWindow);
                }
            });
        }
    }

    public void run() {
        if (this.mTick > 0) {
            this.mTick--;
            if (this.mTick == 0) {
                hideWindow();
            }
        }
    }

    private void hideWindow() {
        HandlerUI.getInstance().post(this.mHideWindow);
    }

    public static void disableDoorWindowLocal(boolean flag) {
        if (sDisableDoorWindowLocal != flag) {
            sDisableDoorWindowLocal = flag;
            calcFlagShowDoorWindow();
        }
    }

    public static void doorWindowEnable(int value) {
        if (sDoorWindowEnable != value) {
            sDoorWindowEnable = value;
            calcFlagShowDoorWindow();
        }
    }

    private static void calcFlagShowDoorWindow() {
        boolean flag = !sDisableDoorWindowLocal && sDoorWindowEnable != 0;
        if (sFlagShowDoorWindow != flag) {
            sFlagShowDoorWindow = flag;
            if (!flag) {
                getInstance().hideWindow();
            }
        }
    }

    public static void clearDoorUpdateCode() {
        sUcDoorEngine = -1;
        sUcDoorFl = -1;
        sUcDoorFr = -1;
        sUcDoorRl = -1;
        sUcDoorRr = -1;
        sUcDoorBack = -1;
    }

    public static DoorHelper getInstance() {
        return INSTANCE;
    }

    private DoorHelper() {
    }

    @Override // com.syu.module.IUiNotify
    public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
        if (sFlagShowDoorWindow && sUcDoorEngine != -1 && sUcDoorFl != -1 && sUcDoorFr != -1 && sUcDoorRl != -1 && sUcDoorBack != -1) {
            showAndRefresh();
        }
    }

    public void showAndRefresh() {
        HandlerUI.getInstance().post(this.SHOW);
    }

    public void buildUi() {
        //View view = new Door_Default(TheApp.getInstance());
        //this.mWindow.dismiss();
        //this.mWindow.setContentView(view);
    }

    public void destroyUi() {
        this.mWindow.setContentView(null);
    }
}
