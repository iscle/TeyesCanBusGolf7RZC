package com.syu.canbus;

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
    private Mode mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDrivingDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setMode(Mode.SINCE_START);

        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (binding.sinceStart.isChecked()) {
                setMode(Mode.SINCE_START);
            } else if (binding.sinceRefuelling.isChecked()) {
                setMode(Mode.SINCE_REFUELLING);
            } else if (binding.longTerm.isChecked()) {
                setMode(Mode.LONG_TERM);
            }
        });
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
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPION_SINCE_START_NEW].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_SINCE_START].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_SINCE_START].addNotify(mNotifyCanbus, 1);

        // Since refuelling
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM_NEW].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_RANGE_LEFT_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_DISTANCE_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_DRIVING_TIME_LONG_TERM].addNotify(mNotifyCanbus, 1);
        DataCanbus.NOTIFY_EVENTS[ConstGolf.U_AVG_SPEED_LONG_TERM].addNotify(mNotifyCanbus, 1);

        // Long-term
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

    private void setMode(Mode mode) {
        this.mode = mode;
        switch (mode) {
            case SINCE_START:
                binding.title.setText(getString(R.string.since_start));
                break;
            case SINCE_REFUELLING:
                binding.title.setText(R.string.since_refuelling);
                break;
            case LONG_TERM:
                binding.title.setText(R.string.long_term);
                break;
        }
        updateAvgConsumption();
        updateDrivingDistance();
        updateRangeLeft();
        updateDrivingTime();
        updateAvgSpeed();
    }

    private void updateAvgConsumption() {
        int data = 0;
        switch (mode) {
            case SINCE_START:
                data = DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPION_SINCE_START_NEW];
                break;
            case SINCE_REFUELLING:
                data = DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING_NEW];
                break;
            case LONG_TERM:
                data = DataCanbus.DATA[ConstGolf.U_AVG_CONSUMPTION_LONG_TERM_NEW];
                break;
        }

        int value = data >>> 8;
        int unit = data & 0xFF;
        if (value != 0) {
            binding.consumption.setText(String.format("%d%c%d %s", value / 10, Utils.decimalSeparator(), value % 10, Golf7Data.mOilUnitXp1[unit]));
        } else {
            binding.consumption.setText(String.format("--%c- %s", Utils.decimalSeparator(), Golf7Data.mOilUnitXp1[unit]));
        }
    }

    private void updateDrivingDistance() {
        int data = 0;
        switch (mode) {
            case SINCE_START:
                data = DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_SINCE_START];
                break;
            case SINCE_REFUELLING:
                data = DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING];
                break;
            case LONG_TERM:
                data = DataCanbus.DATA[ConstGolf.U_DRIVING_DISTANCE_LONG_TERM];
                break;
        }

        int value = data & 0xFFFF;
        int unit = (data >> 29) & 0x01;
        binding.distance.setText(value + " " + Golf7Data.mDistanceUnitXp[unit]);
    }

    private void updateRangeLeft() {
        int data = 0;
        switch (mode) {
            case SINCE_START:
                data = DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_SINCE_START];
                break;
            case SINCE_REFUELLING:
                data = DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING];
                break;
            case LONG_TERM:
                data = DataCanbus.DATA[ConstGolf.U_RANGE_LEFT_LONG_TERM];
                break;
        }

        int value = data & 0x1FFFFFFF;
        int unit = (data >> 29) & 0x01;
        binding.range.setText(value + " " + Golf7Data.mDistanceUnitXp[unit]);
    }

    private void updateDrivingTime() {
        int data = 0;
        switch (mode) {
            case SINCE_START:
                data = DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_SINCE_START];
                break;
            case SINCE_REFUELLING:
                data = DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING];
                break;
            case LONG_TERM:
                data = DataCanbus.DATA[ConstGolf.U_DRIVING_TIME_LONG_TERM];
                break;
        }

        int value = data & 0xFFFFFF;
        binding.time.setText(String.format("%02d:%02d h", value / 60, value % 60));
    }

    private void updateAvgSpeed() {
        int data = 0;
        switch (mode) {
            case SINCE_START:
                data = DataCanbus.DATA[ConstGolf.U_AVG_SPEED_SINCE_START];
                break;
            case SINCE_REFUELLING:
                data = DataCanbus.DATA[ConstGolf.U_AVG_SPEED_SINCE_REFUELLING];
                break;
            case LONG_TERM:
                data = DataCanbus.DATA[ConstGolf.U_AVG_SPEED_LONG_TERM];
                break;
        }

        int unit = (data >> 29) & 0x01;
        int value = data & 0xFFFF;
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
                case ConstGolf.U_AVG_CONSUMPION_SINCE_START_NEW:
                case ConstGolf.U_AVG_CONSUMPTION_LONG_TERM_NEW:
                case ConstGolf.U_AVG_CONSUMPTION_SINCE_REFUELLING_NEW:
                    updateAvgConsumption();
                    break;
                case ConstGolf.U_UNIT_CONSUMPTION:
                    //updateAvgConsumption(DataCanbus.DATA[ConstGolf.U_UNIT_CONSUMPTION]);
                    break;
                case ConstGolf.U_RANGE_LEFT_SINCE_START:
                case ConstGolf.U_RANGE_LEFT_LONG_TERM:
                case ConstGolf.U_RANGE_LEFT_SINCE_REFUELLING:
                    updateRangeLeft();
                    break;
                case ConstGolf.U_SET_UNIT_DISTANCE:
                    //updateRangeLeft(DataCanbus.DATA[ConstGolf.U_SET_UNIT_DISTANCE]);
                    break;
                case ConstGolf.U_DRIVING_DISTANCE_SINCE_START:
                case ConstGolf.U_DRIVING_DISTANCE_LONG_TERM:
                case ConstGolf.U_DRIVING_DISTANCE_SINCE_REFUELLING:
                    updateDrivingDistance();
                    break;
                case ConstGolf.U_DRIVING_TIME_SINCE_START:
                case ConstGolf.U_DRIVING_TIME_LONG_TERM:
                case ConstGolf.U_DRIVING_TIME_SINCE_REFUELLING:
                    updateDrivingTime();
                    break;
                case ConstGolf.U_AVG_SPEED_SINCE_START:
                case ConstGolf.U_AVG_SPEED_LONG_TERM:
                case ConstGolf.U_AVG_SPEED_SINCE_REFUELLING:
                    updateAvgSpeed();
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

    private enum Mode {
        SINCE_START,
        SINCE_REFUELLING,
        LONG_TERM
    }
}
