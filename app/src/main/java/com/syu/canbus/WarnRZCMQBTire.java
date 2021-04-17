package com.syu.canbus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

public class WarnRZCMQBTire {
    private static WarnRZCMQBTire mInstance;
    Context context;
    int ecode = 0;
    int etype = 0;
    private TextView mTextWarn;
    private View sWarnContent;

    public static WarnRZCMQBTire getInstance() {
        if (mInstance == null) {
            mInstance = new WarnRZCMQBTire();
        }
        return mInstance;
    }

    public void showWindowTip(int updateCode, int value) {
        /*PopupWindow window = WarnUtils.getWindow();
        if (value == 0 && updateCode == 386) {
            this.ecode = 0;
            if (window.isShowing()) {
                window.dismiss();
            }
        } else if (value == 0 && updateCode == 387) {
            this.etype = 0;
            if (window.isShowing()) {
                window.dismiss();
            }
        } else {
            initTip(updateCode, value);
            if (this.sWarnContent != null) {
                WarnUtils.showWindow();
                WarnUtils.postDimiss(Callback_0077_XP1_ACCORD9_H.Band_Am);
            }
        }*/
    }

    private void initTip(int updateCode, int value) {
        /*if (this.sWarnContent == null) {
            this.context = TheApp.getInstance();
            this.sWarnContent = LayoutInflater.from(this.context).inflate(R.layout.layout_0160_mqb_tire_warn, (ViewGroup) null, false);
            this.mTextWarn = (TextView) this.sWarnContent.findViewById(R.id.tv_ax5_text);
        }
        if (updateCode == 386) {
            this.ecode = value;
        }
        if (updateCode == 387) {
            this.etype = value;
        }
        switch (this.ecode) {
            case 1:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front left: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front left: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front left: Tire pressure loss");
                        break;
                }
            case 2:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front right: Tire pressure loss");
                        break;
                }
            case 3:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front left / Front right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front left / Front right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front left / Front right: Tire pressure loss");
                        break;
                }
            case 4:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Rear left: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Rear left: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Rear left: Tire pressure loss");
                        break;
                }
            case 5:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front left / Rear left: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front left / Rear left: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front left / Rear left: Tire pressure loss");
                        break;
                }
            case 6:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front right / Rear left: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front right / Rear left: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front right / Rear left: Tire pressure loss");
                        break;
                }
            case 7:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front left / Front right / Rear left: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front left / Front right / Rear left: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front left / Front right / Rear left: Tire pressure loss");
                        break;
                }
            case 8:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Rear right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Rear right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Rear right: Tire pressure loss");
                        break;
                }
            case 9:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front left / Rear right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front left / Rear right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front left / Rear right: Tire pressure loss");
                        break;
                }
            case 10:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front right / Rear right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front right / Rear right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front right / Rear right: Tire pressure loss");
                        break;
                }
            case 11:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front left / Front right / Rear right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front left / Front right / Rear right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front left / Front right / Rear right: Tire pressure loss");
                        break;
                }
            case 12:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Rear left / Rear right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Rear left / Rear right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Rear left / Rear right: Tire pressure loss");
                        break;
                }
            case 13:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front left / Rear left / Rear right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front left / Rear left / Rear right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front left / Rear left / Rear right: Tire pressure loss");
                        break;
                }
            case 14:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front right / Rear left / Rear right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front right / Rear left / Rear right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front right / Rear left / Rear right: Tire pressure loss");
                        break;
                }
            case 15:
                switch (this.etype) {
                    case 2:
                        this.mTextWarn.setText("Front left / Front right / Rear left / Rear right: Check tire pressure");
                        break;
                    case 3:
                        this.mTextWarn.setText("Front left / Front right / Rear left / Rear right: Tire pressure too low");
                        break;
                    case 4:
                        this.mTextWarn.setText("Front left / Front right / Rear left / Rear right: Tire pressure loss");
                        break;
                }
        }
        WarnUtils.getWindow().setContentView(this.sWarnContent);*/
    }
}
