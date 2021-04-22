package com.syu.canbus;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.syu.canbus.databinding.FragmentDrivingDataBinding;
import com.syu.canbus.module.IUiNotify;
import com.syu.canbus.module.canbus.DataCanbus;

public class DrivingDataActivity extends BaseActivity {
    private static final String TAG = "DrivingDataActivity";

    private FragmentDrivingDataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDrivingDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
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
        //DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPION_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPION_SINCE_START_NEW].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_SINCE_START].addNotify(mNotifyCanbus, 1);

        // Since refuelling
        //DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM_NEW].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_LONG_TERM].addNotify(mNotifyCanbus, 1);

        // Long-term
        //DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING_NEW].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING].addNotify(mNotifyCanbus, 1);
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
        //DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPION_SINCE_START].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPION_SINCE_START_NEW].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_START].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_START].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_SINCE_START].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_SINCE_START].removeNotify(mNotifyCanbus);

        // Since refuelling
        //DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM_NEW].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_LONG_TERM].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_LONG_TERM].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_LONG_TERM].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_LONG_TERM].removeNotify(mNotifyCanbus);

        // Long-term
        //DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING_NEW].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_SINCE_REFUELLING].removeNotify(mNotifyCanbus);
    }

    private void updateAvgConsumption(int data) {
        int value = data >>> 8;
        int unit = data & 0xFF;
        Log.d(TAG, "mUpdaterAverageOilNew: " + (value / 10) + "."  + (value % 10) + " " + Golf7Data.mOilUnitXp1[unit]);
        if (value != 0) {
            binding.consumption.setText((value / 10) + "." + (value % 10) + " " + Golf7Data.mOilUnitXp1[unit]);
        } else {
            binding.consumption.setText("--.- " + Golf7Data.mOilUnitXp1[unit]);
        }
    }

    private void updateDrivingDistance(int data) {
        int value = data & 0xFFFF;
        int unit = (data >> 29) & 0x01;
        Log.d(TAG, "updateDrivingDistance: " + value + " " + Golf7Data.mDistanceUnitXp[unit]);
        binding.distance.setText(value + " " + Golf7Data.mDistanceUnitXp[unit]);
    }

    private void updateRangeLeft(int data) {
        int value = data & 0x1FFFFFFF;
        int unit = (data >> 29) & 0x01;
        Log.d(TAG, "updateRangeLeft: " + value + " " + Golf7Data.mDistanceUnitXp[unit]);
        binding.range.setText(value + " " + Golf7Data.mDistanceUnitXp[unit]);
    }

    private void updateDrivingTime(int data) {
        int value = data & 0xFFFFFF;
        Log.d(TAG, "updateDrivingTime: " + (value / 60) + ":" + (value % 60) + " h");
        binding.time.setText(String.format("%02d:%02d h", value / 60, value % 60));
    }

    private void updateAvgSpeed(int data) {
        int unit = (data >> 29) & 0x01;
        int value = data & 0xFFFF;
        Log.d(TAG, "updateAvgSpeed: " + value + " " + Golf7Data.mSpeedUnitXp[unit]);
        binding.speed.setText(value + " " + Golf7Data.mSpeedUnitXp[unit]);
    }

    private void updateCarAnimation(int data) {
        Log.d(TAG, "updateCarAnimation: data: " + data);
    }

    private final IUiNotify mNotifyCanbus = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            Utils.dumpOnNotify(TAG, updateCode, ints, flts, strs);
            switch (updateCode) {
                case ConstGolf.U_AVG_CONSUMPION_SINCE_START:
                    //updateAvgConsumption(DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPION_SINCE_START]);
                    break;
                case ConstGolf.U_AVG_CONSUMPION_SINCE_START_NEW:
                    updateAvgConsumption(DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPION_SINCE_START_NEW]);
                    break;
                case ConstGolf.U_AVG_CONSUMPTION_LONG_TERM:
                    //updateAvgConsumption(DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM]);
                    break;
                case ConstGolf.U_AVG_CONSUMPTION_LONG_TERM_NEW:
                    //updateAvgConsumption(DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM_NEW]);
                    break;
                case ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING:
                    //updateAvgConsumption(DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING]);
                    break;
                case ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING_NEW:
                    //updateAvgConsumption(DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING_NEW]);
                    break;
                case ConstGolf.U_UNIT_CONSUMPTION:
                    //updateAvgConsumption(DataCanbus.DATA[ConstGolf.U_UNIT_CONSUMPTION]);
                    break;
                case ConstGolf.U_RANGE_LEFT_SINCE_START:
                    updateRangeLeft(DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_SINCE_START]);
                    break;
                case ConstGolf.U_RANGE_LEFT_LONG_TERM:
                    //updateRangeLeft(DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_LONG_TERM]);
                    break;
                case ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING:
                    //updateRangeLeft(DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING]);
                    break;
                case ConstGolf.U_SET_UNIT_DISTANCE:
                    //updateRangeLeft(DataCanbus.DATA[ConstGolf.U_SET_UNIT_DISTANCE]);
                    break;
                case ConstGolf.U_DRIVING_DISTANCE_SINCE_START:
                    updateDrivingDistance(DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_SINCE_START]);
                    break;
                case ConstGolf.U_DRIVING_DISTANCE_LONG_TERM:
                    //updateDrivingDistance(DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_LONG_TERM]);
                    break;
                case ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING:
                    //updateDrivingDistance(DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING]);
                    break;
                case ConstGolf.U_DRIVING_TIME_SINCE_START:
                    updateDrivingTime(DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_SINCE_START]);
                    break;
                case ConstGolf.U_DRIVING_TIME_LONG_TERM:
                    //updateDrivingTime(DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_LONG_TERM]);
                    break;
                case ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING:
                    //updateDrivingTime(DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING]);
                    break;
                case ConstGolf.U_AVG_SPEED_SINCE_START:
                    updateAvgSpeed(DataCanbus.DATA[ConstGolf.U_AVG_SPEED_SINCE_START]);
                    break;
                case ConstGolf.U_AVG_SPEED_LONG_TERM:
                    //updateAvgSpeed(DataCanbus.DATA[ConstGolf.U_AVG_SPEED_LONG_TERM]);
                    break;
                case ConstGolf.U_AVG_SPEED_SINCE_REFUELLING:
                    //updateAvgSpeed(DataCanbus.DATA[ConstGolf.U_AVG_SPEED_SINCE_REFUELLING]);
                    break;
                case ConstGolf.U_UNIT_SPEED:
                    //updateAvgSpeed(DataCanbus.DATA[ConstGolf.U_UNIT_SPEED]);
                    break;
                case ConstGolf.U_SET_REMAINING_OIL:
                    updateCarAnimation(DataCanbus.DATA[ConstGolf.U_SET_REMAINING_OIL]);
                    break;
            }
        }
    };
}
