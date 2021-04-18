package com.syu.canbus;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.syu.canbus.module.IUiNotify;
import com.syu.canbus.module.canbus.DataCanbus;

public class DrivingDataActivity extends BaseActivity {
    private static final String TAG = "DrivingDataActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRegisterListener() {
        // Common
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_SET_REMAINING_OIL].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_SET_UNIT_DISTANCE].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_UNIT_SPEED].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_UNIT_TEMPERATURE].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_UNIT_CONSUMPTION].addNotify(mNotifyCanbus, 1);

        // Since start
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPION_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_SINCE_START].addNotify(mNotifyCanbus, 1);

        // Since refuelling
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_LONG_TERM].addNotify(mNotifyCanbus, 1);

        // Long-term
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_SINCE_REFUELLING].addNotify(mNotifyCanbus, 1);

        DataCanbus.PROXY.cmd(ConstGolf.C_CAR_GET_ALL_INFO, new int[]{255}, null, null);
    }

    @Override
    protected void onUnregisterListener() {
        // Common
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_SET_REMAINING_OIL].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_SET_UNIT_DISTANCE].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_UNIT_SPEED].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_UNIT_TEMPERATURE].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_UNIT_CONSUMPTION].removeNotify(mNotifyCanbus);

        // Since start
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPION_SINCE_START].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_START].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_START].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_SINCE_START].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_SINCE_START].removeNotify(mNotifyCanbus);

        // Since refuelling
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_LONG_TERM].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_LONG_TERM].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_LONG_TERM].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_LONG_TERM].removeNotify(mNotifyCanbus);

        // Long-term
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
    }

    private void updateAvgConsumption() {
        {
            int data = DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPION_SINCE_START];
            int value = data & 0xFFFF;
            int unit = (data >> 29) & 0x07;
            if (value != 0xFFFF && unit < 4) {
                Log.d(TAG, "mUpdaterAverageOil: " + value + " " + Golf7Data.mOilUnitXp1[unit]);
            } else if (unit < 4) {
                Log.d(TAG, "mUpdaterAverageOil: " + "--.- " + Golf7Data.mOilUnitXp1[unit]);
            }
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM];
            int value = data & 0xFFFF;
            int unit = (data >> 29) & 0x07;
            if (value != 0xFFFF && unit < 4) {
                Log.d(TAG, "mUpdaterAverageOil: " + value + " " + Golf7Data.mOilUnitXp1[unit]);
            } else if (unit < 4) {
                Log.d(TAG, "mUpdaterAverageOil: " + "--.- " + Golf7Data.mOilUnitXp1[unit]);
            }
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING];
            int value = data & 0xFFFF;
            int unit = (data >> 29) & 0x07;
            if (value != 0xFFFF && unit < 4) {
                Log.d(TAG, "mUpdaterAverageOil: " + value + " " + Golf7Data.mOilUnitXp1[unit]);
            } else if (unit < 4) {
                Log.d(TAG, "mUpdaterAverageOil: " + "--.- " + Golf7Data.mOilUnitXp1[unit]);
            }
        }
    }

    private void updateDrivingDistance() {
        {
            int data = DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_SINCE_START];
            int value = data & 0xFFFF;
            int unit = (data >> 29) & 0x01;
            Log.d(TAG, "updateDrivingDistance: " + value + " " + Golf7Data.mDistanceUnitXp[unit]);
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_LONG_TERM];
            int value = data & 0xFFFF;
            int unit = (data >> 29) & 0x01;
            Log.d(TAG, "updateDrivingDistance: " + value + " " + Golf7Data.mDistanceUnitXp[unit]);
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING];
            int value = data & 0xFFFF;
            int unit = (data >> 29) & 0x01;
            Log.d(TAG, "updateDrivingDistance: " + value + " " + Golf7Data.mDistanceUnitXp[unit]);
        }
    }

    private void updateRangeLeft() {
        {
            int data = DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_SINCE_START];
            int value = data & 0x1FFFFFFF;
            int unit = (data >> 29) & 0x01;
            Log.d(TAG, "updateRangeLeft: " + value + " " + Golf7Data.mDistanceUnitXp[unit]);
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_LONG_TERM];
            int value = data & 0x1FFFFFFF;
            int unit = (data >> 29) & 0x01;
            Log.d(TAG, "updateRangeLeft: " + value + " " + Golf7Data.mDistanceUnitXp[unit]);
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING];
            int value = data & 0x1FFFFFFF;
            int unit = (data >> 29) & 0x01;
            Log.d(TAG, "updateRangeLeft: " + value + " " + Golf7Data.mDistanceUnitXp[unit]);
        }
    }

    private void updateDrivingTime() {
        {
            int data = DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_SINCE_START];
            int value = data & 0xFFFFFF;
            Log.d(TAG, "updateDrivingTime: " + (value / 60) + ":" + (value % 60) + " h");
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_LONG_TERM];
            int value = data & 0xFFFFFF;
            Log.d(TAG, "updateDrivingTime: " + (value / 60) + ":" + (value % 60) + " h");
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING];
            int value = data & 0xFFFFFF;
            Log.d(TAG, "updateDrivingTime: " + (value / 60) + ":" + (value % 60) + " h");
        }
    }

    private void updateAvgSpeed() {
        {
            int data = DataCanbus.DATA[ConstGolf.U_AVG_SPEED_SINCE_START];
            int unit = (data >> 29) & 0x01;
            int value = data & 0xFFFF;
            Log.d(TAG, "updateAvgSpeed: " + value + " " + Golf7Data.mSpeedUnitXp[unit]);
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_AVG_SPEED_LONG_TERM];
            int unit = (data >> 29) & 0x01;
            int value = data & 0xFFFF;
            Log.d(TAG, "updateAvgSpeed: " + value + " " + Golf7Data.mSpeedUnitXp[unit]);
        }
        {
            int data = DataCanbus.DATA[ConstGolf.U_AVG_SPEED_SINCE_REFUELLING];
            int unit = (data >> 29) & 0x01;
            int value = data & 0xFFFF;
            Log.d(TAG, "updateAvgSpeed: " + value + " " + Golf7Data.mSpeedUnitXp[unit]);
        }
    }

    private void updateCarAnimation() {
        Log.d(TAG, "updateCarAnimation: data: " + DataCanbus.DATA[ConstGolf.U_SET_REMAINING_OIL]);
    }

    private final IUiNotify mNotifyCanbus = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            Utils.dumpOnNotify(TAG, updateCode, ints, flts, strs);
            switch (updateCode) {
                case ConstGolf.U_AVG_CONSUMPION_SINCE_START:
                case ConstGolf.U_AVG_CONSUMPTION_LONG_TERM:
                case ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING:
                case ConstGolf.U_UNIT_CONSUMPTION:
                    updateAvgConsumption();
                    break;
                case ConstGolf.U_DRIVING_DISTANCE_SINCE_START:
                case ConstGolf.U_DRIVING_DISTANCE_LONG_TERM:
                case ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING:
                case ConstGolf.U_SET_UNIT_DISTANCE:
                    updateDrivingDistance();
                    break;
                case ConstGolf.U_RANGE_LEFT_SINCE_START:
                case ConstGolf.U_RANGE_LEFT_LONG_TERM:
                case ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING:
                    updateRangeLeft();
                    break;
                case ConstGolf.U_DRIVING_TIME_SINCE_START:
                case ConstGolf.U_DRIVING_TIME_LONG_TERM:
                case ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING:
                    updateDrivingTime();
                    break;
                case ConstGolf.U_AVG_SPEED_SINCE_START:
                case ConstGolf.U_AVG_SPEED_LONG_TERM:
                case ConstGolf.U_AVG_SPEED_SINCE_REFUELLING:
                case ConstGolf.U_UNIT_SPEED:
                    updateAvgSpeed();
                    break;
                case ConstGolf.U_SET_REMAINING_OIL:
                    updateCarAnimation();
                    break;
            }
        }
    };
}
