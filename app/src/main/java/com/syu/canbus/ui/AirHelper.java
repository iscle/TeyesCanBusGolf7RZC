package com.syu.canbus.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemProperties;
import android.view.View;
import android.widget.PopupWindow;
import com.syu.canbus.R;
import com.syu.canbus.module.IUiNotify;
import com.syu.canbus.module.canbus.DataCanbus;
import com.syu.canbus.module.canbus.FinalCanbus;
import com.syu.canbus.util.HandlerUI;
import com.syu.canbus.util.SecondTickThread;
import com.syu.ipc.RemoteModuleProxy;

public class AirHelper implements Runnable {
    private static final AirHelper INSTANCE = new AirHelper();
    public static final IUiNotify REFRESH_ON_SHOW = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            AirHelper.getInstance().refreshOnShow();
        }
    };
    public static final IUiNotify SHOW_AND_REFRESH = new IUiNotify() {
        @Override
        public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
            if (!AirHelper.sFlagShowAirWindow) {
                return;
            }
            if (DataCanbus.DATA[1000] == 589841 || DataCanbus.DATA[1000] == 655377) {
                switch (updateCode) {
                    case 158:
                        if (DataCanbus.DATA[158] == 1) {
                            AirHelper.getInstance().showAndRefresh();
                            return;
                        }
                        return;
                    default:
                        AirHelper.getInstance().showAndRefresh();
                        return;
                }
            } else if (DataCanbus.DATA[1000] == 590253 || DataCanbus.DATA[1000] == 655789 || DataCanbus.DATA[1000] == 721325 || DataCanbus.DATA[1000] == 786861) {
                switch (updateCode) {
                    case 14:
                        AirHelper.getInstance().showAndRefresh();
                        return;
                    default:
                        if (DataCanbus.DATA[14] == 0) {
                            AirHelper.getInstance().showAndRefresh();
                            return;
                        }
                        return;
                }
            } else {
                AirHelper.getInstance().showAndRefresh();
            }
        }
    };
    public static int sAirWindowEnable = 1;
    public static boolean sDisableAirWindowLocal;
    public static boolean sFlagShowAirWindow = true;
    private final Runnable SHOW = new Runnable() {
        /* class com.syu.ui.air.AirHelper.AnonymousClass2 */

        public void run() {
            AirHelper.this.mTick = 6;
            int canbusId = DataCanbus.DATA[1000];
            /*if (TheApp.getConfiguration() == 1) {
                boolean ret = false;
                int[] iArr = AirHelper.this.idAirShowInPortMode;
                int length = iArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (canbusId == iArr[i]) {
                        ret = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (!SystemProperties.getBoolean("persist.syu.showvhprotocol", false) && !ret) {
                    return;
                }
            }*/
            /*if (!AirHelper.this.mWindow.isShowing()) {
                TheApp.addRootView(AirHelper.this.mWindow);
                if (TheApp.rootViewWindowToken() == null) {
                    HandlerUI.getInstance().postDelayed(this, 1);
                    return;
                } else if (TheApp.getConfiguration() == 1) {
                    TheApp.showWindow(AirHelper.this.mWindow, 80, 0, 0);
                } else {
                    TheApp.showWindow(AirHelper.this.mWindow, 48, 0, TheApp.getScreenHeight());
                }
            }
            View view = AirHelper.this.mWindow.getContentView();
            if (view != null) {
                view.invalidate();
            }*/
        }
    };
    //int[] idAirShowInPortMode = {40, 22, 48, 36, 14, 49, 115, 293, 70, 128, 47, 298, 142, 334, 61, 263, 255, 254, 280, 257, 51, 289, 367, 1, 300, 98, 297, 76, 173, 137, 31, 366, 394, 17, 45, 274, 106, 157, 256, 160, FinalCanbus.CAR2_RZC_XP1_DaZhong_GaoErFu7_H, 151, FinalCanbus.CAR_439_DJ_MAZIDA6, FinalCanbus.CAR_439_OuDi_Haval_H9, FinalCanbus.CAR_439_OuDi_Haval_H9_H, FinalCanbus.CAR_439_OuDi_Haval_H9_RS, FinalCanbus.CAR_439_OuDi_Haval_H9_H_RS, FinalCanbus.CAR_RZC_Haval_H9, FinalCanbus.CAR_RZC_Haval_H9_H, FinalCanbus.CAR_439_AY_BYD_SURUI, FinalCanbus.CAR_WC2_JiLiYuanJing, FinalCanbus.CAR_439_RZC_JiangHuai_ALL, FinalCanbus.CAR_439_RZC_JiangHuai_ALL_H, FinalCanbus.CAR_WC2_PSAALL_10, FinalCanbus.CAR_XBS_XP1_15ZhongHuaV3, FinalCanbus.CAR_443_WC2_Tianlai08_12, FinalCanbus.CAR_439_RZC_ZhongTaiSR7, FinalCanbus.CAR_439_RZC_ZhongTaiSR9, FinalCanbus.CAR_439_RZC_ZhongTaiSR9_H, FinalCanbus.CAR_439_HCY_BYD_E6_H, 9, FinalCanbus.CAR_RZC_XP1_16BenTengB50, 132, FinalCanbus.CAR_WC2_HavalH2_H, FinalCanbus.CAR_439_RZC_BenzAll_H, FinalCanbus.CAR_RZC_XP1_17X80_L, FinalCanbus.CAR_RZC_XP1_17X80_H, FinalCanbus.CAR_RZC_XP1_16BenTengB50, FinalCanbus.CAR_RZC_FengShenAX7_18, FinalCanbus.CAR_RZC_FengShenAX7_18H, FinalCanbus.CAR_RZC_XP1_YuanJingX6, FinalCanbus.CAR_RZC_ALL_GM_SP_5, FinalCanbus.CAR_RZC_ALL_GM_SP_6, FinalCanbus.CAR_RZC_ALL_GM_SP_7, FinalCanbus.CAR_RZC_ALL_GM_SP_8, FinalCanbus.CAR_RZC_ALL_GM_SP_9, FinalCanbus.CAR_RZC_ALL_GM_SP_10, FinalCanbus.CAR_RZC_ALL_GM_SP_11, FinalCanbus.CAR_RZC_ALL_GM_SP_12, FinalCanbus.CAR_RZC_ALL_GM_SP_13, FinalCanbus.CAR_RZC_ALL_GM_SP_14, FinalCanbus.CAR_RZC_ALL_GM_SP_15, FinalCanbus.CAR_RZC_ALL_GM_SP_16, FinalCanbus.CAR_RZC_ALL_GM_SP_17, FinalCanbus.CAR_RZC_ALL_GM_SP_18, FinalCanbus.CAR_RZC_ALL_GM_SP_19, FinalCanbus.CAR_RZC_ALL_GM_SP_20, FinalCanbus.CAR_RZC_ALL_GM_SP_21, FinalCanbus.CAR_RZC_ALL_GM_SP_22, FinalCanbus.CAR_RZC_ALL_GM_SP_23, FinalCanbus.CAR_RZC_ALL_GM_SP_24, FinalCanbus.CAR_RZC_ALL_GM_SP_25, FinalCanbus.CAR_RZC_ALL_GM_SP_26, FinalCanbus.CAR_RZC_ALL_GM_SP_27, FinalCanbus.CAR_RZC_ALL_GM_SP_28, FinalCanbus.CAR_RZC_ALL_GM_SP_29, FinalCanbus.CAR_RZC_ALL_GM_SP_30, FinalCanbus.CAR_RZC_ALL_GM_SP_31, FinalCanbus.CAR_RZC_ALL_GM_SP_32, FinalCanbus.CAR_RZC_ALL_GM_SP_33, FinalCanbus.CAR_RZC_ALL_GM_SP_34, FinalCanbus.CAR_RZC_ALL_GM_SP_35, 339, FinalCanbus.CAR_RZC4_PSA_ALL_M, FinalCanbus.CAR_RZC4_PSA_ALL_H, FinalCanbus.CAR_RZC_XP1_18JieTU_X70, FinalCanbus.CAR_RZC_XP1_18JieTU_X70_H, FinalCanbus.CAR_WC2_CHANGAN_19KeSai_Hand, FinalCanbus.CAR_439_OD_BenzAll, FinalCanbus.CAR_439_RCW_MZD_Ruiyi, FinalCanbus.CAR_439_OuDi_NaZhiJieU5, FinalCanbus.CAR_439_OuDi_NaZhiJieU7, FinalCanbus.CAR_443_WC2_Nazhijie_U6, FinalCanbus.CAR_445_WC2_HaiMaM3S7, FinalCanbus.CAR_DJ_XIANDAI_Sorento, FinalCanbus.CAR_DJ_XIANDAI_Sorento_H, FinalCanbus.CAR_439_HC_Benc_C200, FinalCanbus.CAR_BNR_XP1_PsaAll, 47, FinalCanbus.CAR_439_OUDI_BYD_ALL, FinalCanbus.CAR_439_HCY_BYD_S6_H, FinalCanbus.CAR_RZC_ShuPing_15_Camery_AMP, FinalCanbus.CAR_RZC_ShuPing_15_Camery_AMP, FinalCanbus.CAR_RZC_ShuPing_15_Camery_AMP, FinalCanbus.CAR_BNR_ShuPing_Sonata8, FinalCanbus.CAR_BNR_ShuPing_Sonata8_AMP, FinalCanbus.CAR_BNR_ShuPing_Sonata8_AMP_KEEPAIRPANEL, FinalCanbus.CAR_RZC_DaZhong_SUP, FinalCanbus.CAR_RZC_DaZhong_SUP_H, FinalCanbus.CAR_RZC_DaZhong_SUP_M, FinalCanbus.CAR_RZC_DaZhong_MQB_SUP, FinalCanbus.CAR_RZC_DaZhong_MQB_SUP_H, FinalCanbus.CAR_443_WC2_YingFeiNiDi_14QX60_SP, FinalCanbus.CAR_439_RZC_14QiJun_Auto, FinalCanbus.CAR_439_RZC_14QiJun_Hand, FinalCanbus.CAR_RZC_ShuPing_09HighLand_L, FinalCanbus.CAR_RZC_ShuPing_09HighLand_H, FinalCanbus.CAR_BNR_18PradoHand_AirP, FinalCanbus.CAR_XP1_YearGmcSeries, FinalCanbus.CAR_439_HC_Nissan_XiaoKe, 290, FinalCanbus.CAR_RZC_XP1_QiChen18T70, FinalCanbus.CAR_RZC_XP1_QiChen20T70, FinalCanbus.CAR_XP_Renault_20Clio_Low, FinalCanbus.CAR_XP_Renault_20Clio_M, FinalCanbus.CAR_XP_Renault_20Clio_H, FinalCanbus.CAR_452_LUZ_SUBARU, FinalCanbus.CAR_RZC3_XianDai_Qiya_All, FinalCanbus.CAR_452_OD_Cadillac_Escalade, FinalCanbus.CAR_439_RCW_BYD_12_18Surui, FinalCanbus.CAR_439_RCW_BYD_14_15G5, FinalCanbus.CAR_439_RCW_BYD_10_15M6, FinalCanbus.CAR_439_RCW_BYD_13_17S7, FinalCanbus.CAR_439_RCW_BYD_16_19Song, FinalCanbus.CAR_439_RCW_BYD_17_19SongMax, FinalCanbus.CAR_439_RCW_BYD_12_17E6, FinalCanbus.CAR_XP_Renault_05_17Duster, FinalCanbus.CAR_RZC_XP1_15QiRuiRuiHu5, FinalCanbus.CAR_XP1_Meiganna4_TR_HAND, FinalCanbus.CAR_XP1_Meiganna4_TR_AUTO, FinalCanbus.CAR_XP1_Meiganna4_TR_AUTO_H, FinalCanbus.CAR_WeiChi2_18ChangChengH6, FinalCanbus.CAR_WeiChi2_18ChangChengH6_H, FinalCanbus.CAR_WC2_RENAULT_CAPTUR_19, FinalCanbus.CAR_443_WC2_Ford_Lincoln_MKC_V, FinalCanbus.CAR_439_XP_SUBARU_19FORESTER, FinalCanbus.CAR_439_XP_SUBARU_15_17FORESTER, FinalCanbus.CAR_439_XP_SUBARU_15_17CROSSTREK, FinalCanbus.CAR_DAOJUN_XP1_ATSL_High, FinalCanbus.CAR_DAOJUN_XP1_ATS_M};
    int[] idShpOnlyInPortMode = {393645};
    private final Runnable mHideWindow = new Runnable() {
        public void run() {
            AirHelper.this.mWindow.dismiss();
        }
    };
    private int mTick;
    private PopupWindow mWindow;

    private AirHelper() {
    }

    public static AirHelper getInstance() {
        return INSTANCE;
    }

    public void initWindow(Context context) {
        if (this.mWindow == null) {
            SecondTickThread.getInstance().addTick(this);
            this.mWindow = new PopupWindow(context);
            //this.mWindow.setAnimationStyle(R.style.anim_window_pack);
            this.mWindow.setWidth(-2);
            this.mWindow.setHeight(-2);
            this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.mWindow.setOutsideTouchable(true);
            this.mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                public void onDismiss() {
                    //TheApp.removeRootView(AirHelper.this.mWindow);
                }
            });
        }
    }

    public void run() {
        if (this.mTick > 0) {
            this.mTick--;
            if (this.mTick == 0) {
                HandlerUI.getInstance().post(this.mHideWindow);
                if (DataCanbus.DATA[1000] == 589841 || DataCanbus.DATA[1000] == 655377) {
                    RemoteModuleProxy remoteModuleProxy = DataCanbus.PROXY;
                    int[] iArr = new int[2];
                    iArr[0] = 240;
                    remoteModuleProxy.cmd(107, iArr, null, null);
                }
            }
        }
    }

    public void hideWindow() {
        HandlerUI.getInstance().post(this.mHideWindow);
    }

    public static void disableAirWindowLocal(boolean flag) {
        if (sDisableAirWindowLocal != flag) {
            sDisableAirWindowLocal = flag;
            calcFlagShowAirWindow();
        }
    }

    public static void airWindowEnable(int value) {
        if (sAirWindowEnable != value) {
            sAirWindowEnable = value;
            calcFlagShowAirWindow();
        }
    }

    private static void calcFlagShowAirWindow() {
        boolean flag = !sDisableAirWindowLocal && sAirWindowEnable != 0;
        if (sFlagShowAirWindow != flag) {
            sFlagShowAirWindow = flag;
            if (!flag) {
                getInstance().hideWindow();
            }
        }
    }

    public void showAndRefresh() {
        HandlerUI.getInstance().post(this.SHOW);
    }

    public void refreshOnShow() {
        View view;
        if (this.mWindow != null && this.mWindow.isShowing() && (view = this.mWindow.getContentView()) != null) {
            view.invalidate();
        }
    }

    public void buildUi(View view) {
        this.mWindow.dismiss();
        this.mWindow.setContentView(view);
    }

    public void destroyUi() {
        this.mWindow.setContentView(null);
    }
}
