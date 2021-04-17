package com.syu.canbus;

import com.syu.canbus.module.canbus.DataCanbus;
import com.syu.canbus.module.canbus.FinalCanbus;

public class Utils {
    public static boolean isRZCGolf() {
        switch (DataCanbus.DATA[1000]) {
            case FinalCanbus.CAR_RZC_XP1_DaZhong_GaoErFu7 /*{ENCODED_INT: 160}*/:
            case FinalCanbus.CAR2_RZC_XP1_DaZhong_GaoErFu7_H /*{ENCODED_INT: 131232}*/:
            case FinalCanbus.CAR_RZC_XP1_MaiTeng /*{ENCODED_INT: 196768}*/:
            case FinalCanbus.CAR_RZC_TuGuan_L /*{ENCODED_INT: 262304}*/:
            case FinalCanbus.CAR_RZC_TuAng /*{ENCODED_INT: 327840}*/:
            case FinalCanbus.CAR_RZC_DaZhong_MQB_SUP /*{ENCODED_INT: 458912}*/:
            case FinalCanbus.CAR_RZC_DaZhong_MQB_SUP_H /*{ENCODED_INT: 524448}*/:
            case FinalCanbus.CAR2_RZC_DaZhong_Tange /*{ENCODED_INT: 589984}*/:
            case FinalCanbus.CAR2_RZC_DaZhong_MQB_E /*{ENCODED_INT: 655520}*/:
            case FinalCanbus.CAR_RZC_TuGuan_L_20 /*{ENCODED_INT: 721056}*/:
            case FinalCanbus.CAR_RZC_MQB_18_19Lavida_plus /*{ENCODED_INT: 786592}*/:
            case FinalCanbus.CAR_RZC_MQB_11_18Sagitar /*{ENCODED_INT: 852128}*/:
            case FinalCanbus.CAR_RZC_MQB_19Sagitar /*{ENCODED_INT: 917664}*/:
            case FinalCanbus.CAR_RZC_MQB_11_17Passat /*{ENCODED_INT: 983200}*/:
            case FinalCanbus.CAR_RZC_MQB_19Passat /*{ENCODED_INT: 1048736}*/:
            case FinalCanbus.CAR_RZC_MQB_19Tharu /*{ENCODED_INT: 1179808}*/:
            case FinalCanbus.CAR_RZC_MQB_14_19Golf7 /*{ENCODED_INT: 1310880}*/:
            case FinalCanbus.CAR_RZC_MQB_19Bora /*{ENCODED_INT: 1376416}*/:
            case FinalCanbus.CAR_RZC_MQB_14_19Lamando /*{ENCODED_INT: 1441952}*/:
            case FinalCanbus.CAR_RZC_MQB_19Polo /*{ENCODED_INT: 1507488}*/:
            case FinalCanbus.CAR_RZC_MQB_19CC /*{ENCODED_INT: 1573024}*/:
            case FinalCanbus.CAR_RZC_MQB_19T_Cross /*{ENCODED_INT: 1638560}*/:
            case FinalCanbus.CAR_RZC_MQB_16_18TouranL /*{ENCODED_INT: 1704096}*/:
            case FinalCanbus.CAR_RZC_MQB_17_18C_Trek /*{ENCODED_INT: 1769632}*/:
            case FinalCanbus.CAR_RZC_MQB_16_19Golf_jialv /*{ENCODED_INT: 1835168}*/:
            case FinalCanbus.CAR_RZC_MQB_19T_ROC /*{ENCODED_INT: 1900704}*/:
            case FinalCanbus.CAR_RZC_MQB_19Tayron /*{ENCODED_INT: 1966240}*/:
            case FinalCanbus.CAR_RZC_MQB_13_14Octavia /*{ENCODED_INT: 2031776}*/:
            case FinalCanbus.CAR_RZC_MQB_15_19Octavia /*{ENCODED_INT: 2097312}*/:
            case FinalCanbus.CAR_RZC_MQB_13_15Superb /*{ENCODED_INT: 2162848}*/:
            case FinalCanbus.CAR_RZC_MQB_16_18Superb /*{ENCODED_INT: 2228384}*/:
            case FinalCanbus.CAR_RZC_MQB_18Kamiq /*{ENCODED_INT: 2293920}*/:
            case FinalCanbus.CAR_RZC_MQB_18_19Karoq /*{ENCODED_INT: 2359456}*/:
            case FinalCanbus.CAR_RZC_MQB_17_19Kodiaq /*{ENCODED_INT: 2424992}*/:
            case FinalCanbus.CAR_RZC_MQB_14_18Caravan /*{ENCODED_INT: 2490528}*/:
            case FinalCanbus.CAR_RZC_MQB_19Jetta_VS5 /*{ENCODED_INT: 2556064}*/:
            case FinalCanbus.CAR_RZC_MQB_11_17Touareg /*{ENCODED_INT: 2621600}*/:
            case FinalCanbus.CAR_RZC_MQB_09_19Multivan /*{ENCODED_INT: 2687136}*/:
            case FinalCanbus.CAR_RZC_MQB_Jetta_Brazil /*{ENCODED_INT: 2752672}*/:
            case FinalCanbus.CAR_RZC_MQB_SUPERB_Turkey /*{ENCODED_INT: 2818208}*/:
            case FinalCanbus.CAR_RZC_MQB_LAVIDA_19_E /*{ENCODED_INT: 2883744}*/:
            case FinalCanbus.CAR_RZC_MQB_GOLF_20_E /*{ENCODED_INT: 2949280}*/:
            case FinalCanbus.CAR_RZC_MQB_BORA_20_E /*{ENCODED_INT: 3014816}*/:
            case FinalCanbus.CAR_RZC_MQB_Huiang_21 /*{ENCODED_INT: 3080352}*/:
            case FinalCanbus.CAR_RZC_MQB_20Tayron_GTE /*{ENCODED_INT: 3145888}*/:
            case FinalCanbus.CAR_RZC_MQB_20Jetta_VS7 /*{ENCODED_INT: 3211424}*/:
            case FinalCanbus.CAR_RZC_MQB_20Viloran /*{ENCODED_INT: 3276960}*/:
                return true;
            default:
                return false;
        }
    }
}
