package com.syu.canbus;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.lsec.core.frame.ctrl.JRadarContent8;
import com.syu.canbus.module.IUiNotify;
import com.syu.canbus.module.main.DataMain;
import com.syu.canbus.module.main.FinalMain;

public class RadarActivity extends BaseActivity {
    private final static int[] DATA_RADAR = new int[16];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JRadarContent8 radarView = new JRadarContent8(this);
        radarView.setData(DATA_RADAR);
        radarView.setLayoutParams(new ViewGroup.LayoutParams(1024, 600));
        setContentView(radarView);
    }

    @Override
    protected void onRegisterListener() {
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_FL].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_FML].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_FMR].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_FR].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RL].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RML].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RMR].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RR].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RSF].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RSMF].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RSMB].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RSB].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_LSF].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_LSMF].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_LSMB].addNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_LSB].addNotify(mMainNotify);
    }

    @Override
    protected void onUnregisterListener() {
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_FL].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_FML].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_FMR].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_FR].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RL].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RML].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RMR].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RR].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RSF].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RSMF].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RSMB].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_RSB].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_LSF].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_LSMF].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_LSMB].removeNotify(mMainNotify);
        DataMain.NOTIFY_EVENTS[FinalMain.U_RADAR_LSB].removeNotify(mMainNotify);
    }

    private final IUiNotify mMainNotify = (updateCode, ints, flts, strs) -> {
        switch (updateCode) {
            case FinalMain.U_RADAR_FL:
            case FinalMain.U_RADAR_FML:
            case FinalMain.U_RADAR_FMR:
            case FinalMain.U_RADAR_FR:
            case FinalMain.U_RADAR_RL:
            case FinalMain.U_RADAR_RML:
            case FinalMain.U_RADAR_RMR:
            case FinalMain.U_RADAR_RR:
            case FinalMain.U_RADAR_RSF:
            case FinalMain.U_RADAR_RSMF:
            case FinalMain.U_RADAR_RSMB:
            case FinalMain.U_RADAR_RSB:
            case FinalMain.U_RADAR_LSF:
            case FinalMain.U_RADAR_LSMF:
            case FinalMain.U_RADAR_LSMB:
            case FinalMain.U_RADAR_LSB:
                if (ints != null && ints.length > 0) {
                    if (updateCode < 90) {
                        DATA_RADAR[updateCode - 14] = ints[0];
                    } else if (updateCode <= 93) {
                        DATA_RADAR[(updateCode + 12) - 90] = ints[0];
                    } else {
                        DATA_RADAR[(updateCode + 4) - 90] = ints[0];
                    }
                }
                break;
        }
    };
}
